/* 
 * $RCSfile$    $Author$    $Date$    $Revision$
 * 
 * Copyright (C) 1997-2001  The JChemPaint project
 * 
 * Contact: steinbeck@ice.mpg.de
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

package org.openscience.cdk.test;

import org.openscience.cdk.controller.*;
import org.openscience.cdk.*;
import org.openscience.cdk.io.*;
import org.openscience.cdk.layout.*;
import org.openscience.cdk.renderer.*;
import org.openscience.cdk.structgen.*;
import org.openscience.cdk.*;
import org.openscience.cdk.tools.*;
import org.openscience.cdk.smiles.*;
import java.io.*;
import java.util.*;
import junit.framework.*;


public class VicinitySamplerTest extends TestCase
{
	
	public VicinitySamplerTest(String name)
	{
		super(name);
	}

	
	public static Test suite() {
		return new TestSuite(VicinitySamplerTest.class);
	}

	public  void testVicinitySampler()
	{
		Molecule mol = MoleculeFactory.makeEthylPropylPhenantren();
		configureAtoms(mol);
		fixCarbonHCount(mol);
		
		//System.out.println("Initial Molecule: \n" + mol);
		VicinitySampler vs = new VicinitySampler();
		vs.setMolecule(mol);

		SmilesGenerator sg = null;
		Molecule temp = null;
		Vector structures = vs.sample((AtomContainer) mol);
		structures.addElement(mol);
		for (int f = 0; f < structures.size(); f++)
		{
			temp = (Molecule)structures.elementAt(f);
			sg = new SmilesGenerator();
			System.out.println(sg.createSMILES(temp));
		}

		System.out.println("There are " + structures.size() + " structures in Faulon-Distance 1 for EthylPropylPhenantren"); 
		display(structures);
	}
	private static void configureAtoms(Molecule mol)
	{
		try
		{
			new ElementFactory().configureAtoms(mol);
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
	}

	
	private void fixCarbonHCount(Molecule mol)
	{	
		/* the following line are just a quick fix for this
		   particluar carbon-only molecule until we have a proper 
		   hydrogen count configurator
		 */
		int bondCount = 0;
		Atom atom;
		 for (int f = 0; f < mol.getAtomCount(); f++)
		{
			atom = mol.getAtomAt(f);
			bondCount =  mol.getBondOrderSum(atom);
			if (bondCount > 4) System.out.println("bondCount: " + bondCount);
			atom.setHydrogenCount(4 - bondCount - (int)atom.getCharge());
		}
	}

	
	private void display(Vector structures)
	{
		MoleculeListViewer moleculeListViewer = new MoleculeListViewer();
		moleculeListViewer.setDefaultCloseOperation(moleculeListViewer.EXIT_ON_CLOSE);
		StructureDiagramGenerator sdg = null;
		MoleculeViewer2D mv = null;
		Molecule mol = null;
		for (int f = 0; f < structures.size(); f++)
		{
			sdg = new StructureDiagramGenerator();
			mv = new MoleculeViewer2D();
			mv.getRenderer2DModel().setDrawNumbers(true);
			mol = (Molecule)structures.elementAt(f);
			sdg.setMolecule((Molecule)mol.clone());

			try
			{
				sdg.generateCoordinates();
				mv.setAtomContainer(sdg.getMolecule());
				moleculeListViewer.addStructure(mv, "no. " + (f + 1));

			}
			catch(Exception exc)
			{
				
				exc.printStackTrace();
			}
		}
	}

	
	public static void main(String[] args)
	{
		VicinitySamplerTest vst = new VicinitySamplerTest("VicinitySamplerTest");
		vst.testVicinitySampler();
	}
}
