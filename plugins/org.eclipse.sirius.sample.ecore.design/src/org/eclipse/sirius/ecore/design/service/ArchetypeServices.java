/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.design.service;

import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;

/**
 * Services usable from a VSM to deal with archetypes.
 */
public class ArchetypeServices {
	private static final String ARCHETYPE_URI = "http://www.eclipse.org/sirius/dnc/archetype";

	public void addArchetypeAnnotation(EClass clazz, String archetype) {
		EAnnotation annot = clazz.getEAnnotation(ARCHETYPE_URI);
		if (annot == null) {
			annot = EcoreFactory.eINSTANCE.createEAnnotation();
			annot.setSource(ARCHETYPE_URI);
			clazz.getEAnnotations().add(annot);
		}
		annot.getDetails().put("archetype", archetype);
	}

	public boolean isMomentInterval(EObject clazz) {
		return hasArchetypeAnnotation(clazz, "MomentInterval");
	}

	public boolean isDescription(EObject clazz) {
		return hasArchetypeAnnotation(clazz, "Description");
	}

	public boolean isThing(EObject clazz) {
		return hasArchetypeAnnotation(clazz, "Thing");
	}

	public boolean isRole(EObject clazz) {
		return hasArchetypeAnnotation(clazz, "Role");
	}

	private boolean hasArchetypeAnnotation(EObject clazz, String string) {
		if (clazz instanceof EModelElement) {
			EAnnotation annot = ((EModelElement) clazz).getEAnnotation(ARCHETYPE_URI);
			if (annot != null) {
				for (Map.Entry<String, String> entry : annot.getDetails()
						.entrySet()) {
					if ("archetype".equals(entry.getKey())
							&& string.equals(entry.getValue())) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
