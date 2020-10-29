package test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Executor executor = Executors.newFixedThreadPool(3);
		
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
		    try {
		        TimeUnit.SECONDS.sleep(2);
		    } catch (InterruptedException e) {
		       throw new IllegalStateException(e);
		    }
		    return "Result of Future 1:" +Thread.currentThread().getName();
		}, executor);

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
		    try {
		        TimeUnit.SECONDS.sleep(1);
		    } catch (InterruptedException e) {
		       throw new IllegalStateException(e);
		    }
		    return "Result of Future 2:" +Thread.currentThread().getName();
		}, executor);

		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
		    try {
		        TimeUnit.SECONDS.sleep(3);
		    } catch (InterruptedException e) {
		       throw new IllegalStateException(e);
		    }
		    return "Result of Future 3:" +Thread.currentThread().getName();
		}, executor);
		
		CompletableFuture.allOf(future1, future2, future3).get();
		
		System.out.println(future1.get());
		System.out.println(future2.get());
		System.out.println(future3.get());

	}
}
