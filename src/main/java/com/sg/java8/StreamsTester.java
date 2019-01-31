package com.sg.java8;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class StreamsTester {

	public static void main( String[] args ) {
		// 1. Collections methods using lambda
		final List<String> names = Arrays.asList( "Sachin", "Puja", "Aditi", "Manju" );
		names.forEach( System.out::println );
		names.forEach( s -> System.out.println( s ) );

		// 2. Collections using streams and parallel streams
		final List<String> addresses = Arrays.asList( "Agra", "Bareilly", "Fzd", "Edin" );

		addresses.stream().forEach( s -> s.toUpperCase() );
		System.out.println( addresses ); //--- NOPE

		System.out.println(
				addresses.parallelStream().map( s -> s.toUpperCase() ).collect( toList() ) );

		/* 3. Difference between streams and parallal streams*/
		final long startTime = System.currentTimeMillis();
		System.out.println( addresses.parallelStream()
				.peek( s -> sleepUninterrupted( 500L ) )
				.collect( toList() ) );

		final long endTime = System.currentTimeMillis();
		System.out.println( "Time it took to peek: " + ( endTime - startTime ) );

		// 4. Streams creating streams using range
		IntStream.range( 0, 20 ).forEach( i -> System.out.println( i ) );

		// 5. Order of elements in two lists are different because of parallelism
		final List<Integer> otherIntsList = Collections.synchronizedList( new ArrayList<>() );
		final List<Integer> twentyInts = IntStream.range( 0, 20 )
				.parallel()
				.boxed()
				.peek( i -> otherIntsList.add( i ) )
				.collect( toList() );

		System.out.println( twentyInts );
		System.out.println( otherIntsList );

		//6. Sum (Test of reduce)
		//Bad
		long[] sum = { 0L };
		LongStream.rangeClosed( 1, 100_000_000L )
				//.parallel()
				.forEach( l -> sum[0] += l );

		System.out.println( sum[0] );

		//less Bad
		final AtomicLong atomicSum = new AtomicLong( 0 );
		LongStream.rangeClosed( 1, 100_000_000L )
				.parallel()
				.forEach( l -> atomicSum.addAndGet( l ) );

		System.out.println( atomicSum.get() );

		// Sum using reduce
		System.out.println( LongStream.rangeClosed( 1, 100_000_000L )
				.parallel()
				.reduce( 0, ( i, j ) -> i + j ) );

		// Sum using sum, which is same as reduce internally
		System.out.println( LongStream.rangeClosed( 1, 100_000_000L ).parallel().sum() );
	}

	static void sleepUninterrupted( final long timeMs ) {
		try {
			Thread.sleep( timeMs );
		} catch ( InterruptedException e ) {
		}
	}

}
