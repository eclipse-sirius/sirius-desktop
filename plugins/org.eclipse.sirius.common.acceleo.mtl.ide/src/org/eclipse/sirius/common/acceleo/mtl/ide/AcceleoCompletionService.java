/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.mtl.ide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.acceleo.internal.ide.ui.editors.template.AcceleoCompletionProcessor;
import org.eclipse.acceleo.internal.ide.ui.editors.template.AcceleoCompletionProposal;
import org.eclipse.acceleo.internal.ide.ui.editors.template.AcceleoSourceContent;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter.DynamicAcceleoModule;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposalBuilder;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposalWithReplacement.ImageKind;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Service to provide completion for the Acceleo MTL interpreter.
 * 
 * @author mporhel
 */
public class AcceleoCompletionService {

    private final Collection<URI> dependencies;

    private final Set<String> ignoreList;

    private final ResourceSet resourceSet;

    private final String moduleContent;

    /**
     * Constructor.
     * 
     * @param moduleContent
     *            the dynamic module.
     * @param module
     *            The dynamic acceleo module in which our dependencies have been
     *            compiled.
     * @param dependencies
     *            the declated imported mtl files
     */
    public AcceleoCompletionService(String moduleContent, DynamicAcceleoModule module, Collection<URI> dependencies) {
        this.moduleContent = moduleContent;
        this.ignoreList = module.getGeneratedQueryNames();
        this.resourceSet = module.getResourceSet();
        this.dependencies = dependencies;
    }

    /**
     * Compute a list of proposals for the given context.
     * 
     * @param contextText
     *            the text to complete.
     * @param cursorPosition
     *            the current cursor position.
     * @param moduleElementName
     *            the name of the edited module element.
     * @return a list of {@link ContentProposal}
     */
    public synchronized List<ContentProposal> getProposals(String contextText, int cursorPosition, String moduleElementName) {
        List<ContentProposal> proposals = new ArrayList<>();

        AcceleoSourceContent src = new AcceleoSourceContent() {
            {
                /*
                 * We compiled in-memory and thus our dependencies may not be
                 * all accessible from disc. Use the existing resource set
                 * instead of trying to re-load/re-compile everything.
                 */
                this.syntaxHelpResourceSet = new ResourceSetImpl();
                this.syntaxHelpResourceSet.setResourceFactoryRegistry(resourceSet.getResourceFactoryRegistry());
                for (Resource res : resourceSet.getResources()) {
                    final Resource copy = this.syntaxHelpResourceSet.createResource(res.getURI());
                    for (EObject root : res.getContents()) {
                        copy.getContents().add(EcoreUtil.copy(root));
                    }
                    this.syntaxHelpResourceSet.getResources().add(copy);
                }
            }

            @Override
            public List<URI> getAccessibleOutputFiles() {
                return getDeclaredImports();
            }
        };
        src.init(new StringBuffer(moduleContent));

        String expression = contextText.substring(1);
        int position = -1;
        // Add a trailing parenthesis in case we have more than 10 queries.
        final int queryIndex = moduleContent.indexOf(moduleElementName + '(');

        if (queryIndex > 0) {
            position = moduleContent.indexOf(expression, queryIndex);
        }

        if (position > 0) {
            // remove one : we have a leading [ in the text, but not in the
            // module
            position += cursorPosition - 1;

            AcceleoCompletionProcessor processor = new AcceleoCompletionProcessor(src);
            ICompletionProposal[] props = processor.computeCompletionProposals(null, position);

            for (ICompletionProposal prop : props) {
                String displayString = prop.getDisplayString();
                if (!(displayString.contains("(") && ignoreList.contains(displayString.substring(0, displayString.indexOf("("))))) { //$NON-NLS-1$ //$NON-NLS-2$
                    if (prop instanceof AcceleoCompletionProposal) {
                        AcceleoCompletionProposal acceleoCompletionProposal = (AcceleoCompletionProposal) prop;

                        String proposalText = getCleanedProposalText(acceleoCompletionProposal.getReplacementString());
                        String information = getCleanedAdditionalText(prop.getAdditionalProposalInfo());

                        final int offset = acceleoCompletionProposal.getReplacementOffset() - position + cursorPosition;
                        final int length = acceleoCompletionProposal.getReplacementLength();

                        ContentProposal proposal = ContentProposalBuilder.proposal(proposalText, prop.getDisplayString(), information).withReplacement(offset, length)
                                .withImage(acceleoCompletionProposal.getImage(), ImageKind.SWT_IMAGE).build();

                        proposals.add(proposal);
                    } else {
                        IDocument doc = new Document(moduleContent);
                        String end = doc.get().substring(position, moduleContent.indexOf(']', position) + 1);
                        prop.apply(doc);

                        final String completedText = doc.get();
                        final String proposalText = this.getCleanedProposalText(completedText.substring(position, completedText.indexOf(end, position)));
                        final String information = getCleanedAdditionalText(prop.getAdditionalProposalInfo());

                        ContentProposal proposal = ContentProposalBuilder.proposal(proposalText, displayString, information).withReplacement(cursorPosition, 0)
                                .withImage(prop.getImage(), ImageKind.SWT_IMAGE).build();

                        proposals.add(proposal);
                    }
                }
            }
        }
        return proposals;
    }

    /**
     * Remove the "${variable}" parts in the completion. There are needed in the
     * context of a full editor, but here they just add noise.
     */
    private String getCleanedProposalText(String s) {
        return s.replaceAll("\\$\\{[^\\}]+\\}", ""); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Remove the HTML-like tags from the tooltip text, as they not supported
     * with the current tooltips/completion code.
     */
    private String getCleanedAdditionalText(String s) {
        return s.replaceAll("<br/>", "\n").replaceAll("<[^>]+>", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    }

    private List<URI> getDeclaredImports() {
        List<URI> accessibleOutputFiles = new ArrayList<>();
        if (dependencies != null) {
            Iterables.addAll(accessibleOutputFiles, Iterables.filter(dependencies, Predicates.notNull()));
        }
        return accessibleOutputFiles;
    }
}
