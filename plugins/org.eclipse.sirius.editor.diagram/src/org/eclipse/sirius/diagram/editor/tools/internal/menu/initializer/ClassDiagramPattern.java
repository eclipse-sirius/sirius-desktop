/*******************************************************************************
 * Copyright (c) 2014 - Joao Martins and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Joao Martins <joaomartins27396@gmail.com>  - initial API and implementation 
 *   Maxime Porhel <maxime.porhel@obeo.fr> Obeo - Bug 438074, remarks and correction during review.
 *******************************************************************************/

package org.eclipse.sirius.diagram.editor.tools.internal.menu.initializer;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * This is a abstract class to provide a implementation to pattern provider.
 * 
 * @author Joao Martins
 * 
 */
public class ClassDiagramPattern implements IPatternProvider {

    /**
     * Id.
     */
    private static final String DEFAUT_BASE_ID = "pattern.class.diagram";

    /**
     * Label.
     */
    private static final String LABEL = "ClassDiagramPatternProvider";

    /**
     * Description.
     */
    private static final String DESCRIPTION = "This pattern produces a Class Diagram skeleton to represent the following concepts: Package, Class, Attribute, Reference and Inheritance. Each concept has its mapping and creation tool. The delete and direct edit tools creation can be configured. The relations mappings and tools or other mappings are set.";

    /**
     * ImagePath.
     */
    private static final String IMAGEPATH = "images/classDiagramPattern.png";

    private boolean globalEditTool;

    private boolean globalDeleteTool;
    
    private String baseId = DEFAUT_BASE_ID;

    /**
     * Default Constructor.
     */
    public ClassDiagramPattern() {
        globalEditTool = false;
        globalDeleteTool = false;
    }

    /**
     * set pattern configuration.
     * 
     * @param gEditTool
     *            to single or multiple edit tool.
     * @param gDeleteTool
     *            to single or multiple delete tool.
     */
    public void setPatternConfiguration(boolean gEditTool, boolean gDeleteTool) {
        this.globalEditTool = gEditTool;
        this.globalDeleteTool = gDeleteTool;
    }

    @Override
    public Command getPatternCreationCommand(ResourceSet resourceSet, Viewpoint viewpoint) {
        return new ClassDiagramSkeleteonCreationCommand(resourceSet, viewpoint, globalEditTool, globalDeleteTool, baseId);
    }
    
    @Override
    public String getId() {
        return DEFAUT_BASE_ID;
    }
    public String getBaseId() {
        return baseId;
    }
    
    public void setBaseId(String id) {
        baseId = id;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getImagePath() {
        return IMAGEPATH;
    }

}
