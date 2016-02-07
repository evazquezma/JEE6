package es.pruebas.sisifo.async.service.wait;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WaitUntilFinishService<T> {
	
	@Async
	public void waitUntilFinish (Future<T> future, AfterWaitComand<T> afterWaitCommand){
		try {
			T result = future.get();
			afterWaitCommand.docommand(result);
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("The task was interrupted");
			e.printStackTrace();
		}
	}
}
