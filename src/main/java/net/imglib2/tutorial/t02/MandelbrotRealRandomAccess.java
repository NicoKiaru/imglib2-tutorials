/*
 * #%L
 * ImgLib2: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2016 Tobias Pietzsch, Stephan Preibisch, Stephan Saalfeld,
 * John Bogovic, Albert Cardona, Barry DeZonia, Christian Dietz, Jan Funke,
 * Aivar Grislis, Jonathan Hale, Grant Harris, Stefan Helfrich, Mark Hiner,
 * Martin Horn, Steffen Jaensch, Lee Kamentsky, Larry Lindsey, Melissa Linkert,
 * Mark Longair, Brian Northan, Nick Perry, Curtis Rueden, Johannes Schindelin,
 * Jean-Yves Tinevez and Michael Zinsmaier.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package net.imglib2.tutorial.t02;

import net.imglib2.RealPoint;
import net.imglib2.RealRandomAccess;
import net.imglib2.type.numeric.integer.UnsignedByteType;

/**
 * A RealRandomAccess that procedurally generates values (iteration count)
 * for the mandelbrot set.
 *
 * @author Tobias Pietzsch
 */
public class MandelbrotRealRandomAccess extends RealPoint implements RealRandomAccess< UnsignedByteType >
{
	final UnsignedByteType t;

	public MandelbrotRealRandomAccess()
	{
		super( 2 ); // number of dimensions is 2
		t = new UnsignedByteType();
	}

	public static final int mandelbrot( final double re0, final double im0, final int maxIterations )
	{
		double re = re0;
		double im = im0;
		int i = 0;
		for ( ; i < maxIterations; ++i )
		{
			final double squre = re * re;
			final double squim = im * im;
			if ( squre + squim > 4 )
				break;
			im = 2 * re * im + im0;
			re = squre - squim + re0;
		}
		return i;
	}

	@Override
	public UnsignedByteType get()
	{
		t.set( mandelbrot( position[ 0 ], position[ 1 ], 255 ) );
		return t;
	}

	@Override
	public MandelbrotRealRandomAccess copyRealRandomAccess()
	{
		return copy();
	}

	@Override
	public MandelbrotRealRandomAccess copy()
	{
		final MandelbrotRealRandomAccess a = new MandelbrotRealRandomAccess();
		a.setPosition( this );
		return a;
	}
}
