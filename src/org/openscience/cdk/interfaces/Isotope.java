/* $RCSfile$
 * $Author$
 * $Date$
 * $Revision$
 *
 * Copyright (C) 1997-2005  The Chemistry Development Kit (CDK) project
 *
 * Contact: cdk-devel@lists.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All I ask is that proper credit is given for my work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package org.openscience.cdk.interfaces;

/**
 * Used to store and retrieve data of a particular isotope.
 *
 * @cdk.module data
 *
 * @author      egonw
 * @cdk.created 2005-08-24
 * @cdk.keyword isotope
 */
public interface Isotope extends Element {

	/**
	 * Sets the NaturalAbundance attribute of the Isotope object.
	 *
	 * @param  naturalAbundance  The new NaturalAbundance value
     *
     * @see    #getNaturalAbundance
	 */
	public void setNaturalAbundance(double naturalAbundance);

	/**
	 * Sets the ExactMass attribute of the Isotope object.
	 *
	 * @param  exactMass  The new ExactMass value
     *
     * @see    #getExactMass
	 */
	public void setExactMass(double exactMass);

	/**
	 * Gets the NaturalAbundance attribute of the Isotope object.
	 * Returns null when unconfigured.
	 *
	 * @return    The NaturalAbundance value
     *
     * @see       #setNaturalAbundance
	 */
	public double getNaturalAbundance();

	/**
	 * Gets the ExactMass attribute of the Isotope object.
	 * Returns null when unconfigured.
	 *
	 * @return    The ExactMass value
     *
     * @see       #setExactMass
	 */
	public double getExactMass();

    /**
     * Returns the atomic mass of this element.
	 * Returns null when unconfigured.
     *
     * @return The atomic mass of this element
     *
     * @see    #setMassNumber(int)
     */
    public int getMassNumber();
    
    /**
     * Sets the atomic mass of this element.
     *
     * @param   massNumber The atomic mass to be assigned to this element
     *
     * @see    #getMassNumber
     */
    public void setMassNumber(int massNumber);
    
}

