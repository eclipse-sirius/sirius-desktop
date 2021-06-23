/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.business.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram;
import org.eclipse.sirius.diagram.sequence.template.TemplateFactory;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;
import org.eclipse.sirius.diagram.sequence.tool.internal.template.TemplateToDiagramDescriptionTransformer;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.business.api.template.RepresentationTemplateEdit;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;

/**
 * Implementation of a representation template Edit for the sequence diagram template.
 * 
 * @author cbrun
 * 
 */
public class SequenceDiagramTemplateEdit implements RepresentationTemplateEdit {
    /**
     * {@inheritDoc}
     */
    @Override
    public Object getNewChildDescriptor() {
        return new CommandParameter(null, DescriptionPackage.Literals.VIEWPOINT__OWNED_TEMPLATES, TemplateFactory.eINSTANCE.createTSequenceDiagram());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject getSourceElement(EObject vsmObject) {
        Option<TSequenceDiagram> result = new EObjectQuery(vsmObject).getParentSequenceDiagramTemplate();
        if (result.some()) {
            TSequenceDiagram template = result.get();
            ECrossReferenceAdapter crosser = getOrCreateCrossReferencer(template);
            for (Setting setting : crosser.getInverseReferences(vsmObject)) {
                if (setting.getEStructuralFeature() == TemplatePackage.eINSTANCE.getTTransformer_Outputs()) {
                    return setting.getEObject();
                }

            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(RepresentationTemplate template) {
        if (template instanceof TSequenceDiagram) {
            TemplateToDiagramDescriptionTransformer transformer = new TemplateToDiagramDescriptionTransformer((TSequenceDiagram) template);
            transformer.refresh();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGenerated(EObject vsmObject) {
        return getSourceElement(vsmObject) != null;
    }

    private ECrossReferenceAdapter getOrCreateCrossReferencer(TSequenceDiagram template) {
        ECrossReferenceAdapter crosser = ECrossReferenceAdapter.getCrossReferenceAdapter(template);
        if (crosser == null) {
            crosser = new ECrossReferenceAdapter();
            template.eAdapters().add(crosser);
        }
        return crosser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOverriden(EObject eObj, EStructuralFeature feature) {
        Option<TSequenceDiagram> result = new EObjectQuery(eObj).getParentSequenceDiagramTemplate();
        if (result.some()) {
            TSequenceDiagram template = result.get();
            return new TemplateToDiagramDescriptionTransformer(template).isOverriding(eObj, feature);
        }
        return false;
    }
}
