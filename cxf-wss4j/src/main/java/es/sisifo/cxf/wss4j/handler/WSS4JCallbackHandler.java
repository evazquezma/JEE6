package es.sisifo.cxf.wss4j.handler;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WSS4JCallbackHandler implements CallbackHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(WSS4JCallbackHandler.class);

	private Map<String, String> passwordsIdentifiers;

	public void setPasswordsIdentifiers(final Map<String, String> passwordsIdentifiers) {
		this.passwordsIdentifiers = passwordsIdentifiers;
	}

	@Override
	public void handle(final Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		LOGGER.debug("Entrada en handle de ClientCallbackHandler");
		for (final Callback callback : callbacks) {
			if (callback instanceof WSPasswordCallback) {
				final WSPasswordCallback pc = (WSPasswordCallback) callback;

				if (pc.getUsage() == WSPasswordCallback.DECRYPT || pc.getUsage() == WSPasswordCallback.SIGNATURE) {
					pc.setPassword(obtenerPassword(pc.getIdentifier()));
				}
			}
		}
	}

	private String obtenerPassword(final String identifier) {
		if (passwordsIdentifiers.containsKey(identifier)) {
			return passwordsIdentifiers.get(identifier);
		}

		LOGGER.error("No se encuentra la contraseña para el alias:{}", identifier);
		throw new RuntimeException("No se encuentra la contraseña ±a para el alias:" + identifier + ".");
	}
}