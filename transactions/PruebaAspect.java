package es.pruebas;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Aspect
public class PruebaAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PruebaAspect.class);


    //Buscar todos los métodos transactional
    @Pointcut("execution(@org.springframework.transaction.annotation.Transactional * *(..))")
    public void transactionalMethods() {}

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void transactionalClasses() {}

    @Pointcut("transactionalMethods() || transactionalClasses()")
    public void transaccionals() {}


/*
    @Before("transaccionals()")
    public void beforeTraceMethods(final JoinPoint joinPoint) {
        System.out.println("before");
    }
*/


    @Around("transaccionals()")
    public Object aroundAdvice(final ProceedingJoinPoint joinPoint) throws Throwable {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            final TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
            LOGGER.info("Hay trasacción iniciada {} is new {}", TransactionSynchronizationManager.getCurrentTransactionName(), status.isNewTransaction());
            if (status.isNewTransaction()) {
                TransactionSynchronizationManager.registerSynchronization(new CustomTransactionSynchronization(TransactionSynchronizationManager.getCurrentTransactionName()));
            }
         }

        Object returnObject = null;
        try {
            LOGGER.debug("Invoking joinPoint...");
            returnObject = joinPoint.proceed();
        }
        catch (final Throwable throwable) {
            throw throwable;
        }
        finally {
            LOGGER.debug("Returning from aspect...");
        }
        return returnObject;
    }

}



class CustomTransactionSynchronization implements TransactionSynchronization {
    private final String txName;

    public CustomTransactionSynchronization(final String currentTransactionName) {
        this.txName = currentTransactionName;
    }


    @Override
    public void suspend() {
      System.out.println(txName + " suspend");

    }

    @Override
    public void resume() {
        System.out.println(txName + " resume");
    }

    @Override
    public void flush() {
        System.out.println(txName + " flush");
    }

    @Override
    public void beforeCommit(final boolean readOnly) {
        System.out.println(txName + " beforeCommit " + readOnly);

    }

    @Override
    public void beforeCompletion() {
        System.out.println(txName + " beforeCompletion");
    }

    @Override
    public void afterCommit() {
        System.out.println(txName + " afterCommit");
    }

    @Override
    public void afterCompletion(final int status) {
        System.out.println(txName + " afterCompletion");

    }
}