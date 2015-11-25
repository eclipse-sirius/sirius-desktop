package org.eclipse.sirius.ui.properties.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.eef.EEFContainerDescription;
import org.eclipse.eef.EEFGroupDescription;
import org.eclipse.eef.EEFPageDescription;
import org.eclipse.eef.EEFTextDescription;
import org.eclipse.eef.EEFViewDescription;
import org.eclipse.eef.EefFactory;
import org.eclipse.eef.core.api.EEFExpressionUtils;
import org.eclipse.eef.core.api.EEFPage;
import org.eclipse.eef.core.api.EEFVariableManagerFactory;
import org.eclipse.eef.core.api.EEFView;
import org.eclipse.eef.core.api.EEFViewFactory;
import org.eclipse.eef.core.api.IVariableManager;
import org.eclipse.eef.ide.ui.internal.data.EEFTabDescriptor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.interpreter.api.IInterpreterProvider;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptorProvider;

public class SiriusTabDescriptorProvider implements ITabDescriptorProvider {

    @Override
    public ITabDescriptor[] getTabDescriptors(IWorkbenchPart part, ISelection selection) {
        Platform.getAdapterManager().registerAdapters(new SiriusSemanticAdapter(), DSemanticDecorator.class);
        Platform.getAdapterManager().registerAdapters(new SiriusSemanticAdapter(), GraphicalEditPart.class);
        Platform.getAdapterManager().registerAdapters(new SiriusSemanticAdapter(), ConnectionEditPart.class);

        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Object[] objects = structuredSelection.toArray();

            // FIXME We take the first one
            if (objects.length > 0) {
                Object object = objects[0];

                EObject semanticElement = null;
                if (object instanceof GraphicalEditPart) {
                    semanticElement = ((GraphicalEditPart) object).resolveSemanticElement();
                } else if (object instanceof ConnectionEditPart) {
                    semanticElement = ((Edge) ((ConnectionEditPart) object).getModel()).getElement();
                } else if (object instanceof DSemanticDecorator) {
                    semanticElement = ((DSemanticDecorator) object).getTarget();
                }

                if (semanticElement != null) {
                    // Let's find out the description of the view
                    return this.getTabDescriptors(semanticElement);
                }
            }
        }
        return new ITabDescriptor[0];
    }

    private ITabDescriptor[] getTabDescriptors(EObject semanticElement) {
        Session session = new EObjectQuery(semanticElement).getSession();
        if (session != null && semanticElement instanceof DSemanticDecorator) {
            DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) semanticElement;
            EObject eObject = dSemanticDecorator.getTarget();

            Set<Resource> resources = new LinkedHashSet<Resource>();
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(true);
            for (Viewpoint viewpoint : selectedViewpoints) {
                Resource eResource = viewpoint.eResource();
                if (eResource != null) {
                    resources.add(eResource);
                }
            }

            List<ViewExtensionDescription> descriptions = new ArrayList<ViewExtensionDescription>();
            for (Resource resource : resources) {
                Iterable<EObject> iterable = AllContents.of(resource.getContents().get(0), PropertiesPackage.Literals.VIEW_EXTENSION_DESCRIPTION);
                for (EObject object : iterable) {
                    if (object instanceof ViewExtensionDescription) {
                        descriptions.add((ViewExtensionDescription) object);
                    }
                }
            }

            // FIXME We take only the firts one
            if (descriptions.size() > 0) {
                ViewExtensionDescription viewExtensionDescription = descriptions.get(0);
                EEFViewDescription eefViewDescription = this.convert(viewExtensionDescription);

                IVariableManager variableManager = new EEFVariableManagerFactory().createVariableManager();
                variableManager.put(EEFExpressionUtils.EEFView.VIEW_SEMANTIC_CANDIDATE, eObject);
                variableManager.put("self", eObject);

                List<IInterpreterProvider> interpreterProviders = new ArrayList<IInterpreterProvider>();
                interpreterProviders.add(new SiriusInterpreterProvider(session));
                EEFView eefView = new EEFViewFactory().createEEFView(eefViewDescription, variableManager, interpreterProviders, session.getTransactionalEditingDomain());
                List<ITabDescriptor> descriptors = new ArrayList<ITabDescriptor>();

                List<EEFPage> eefPages = eefView.getPages();
                for (EEFPage eefPage : eefPages) {
                    EEFTabDescriptor eefTabDescriptor = new EEFTabDescriptor(eefPage);
                    descriptors.add(eefTabDescriptor);
                }

                return descriptors.toArray(new ITabDescriptor[descriptors.size()]);
            }

        }

        return new ITabDescriptor[0];
    }

    private EEFViewDescription convert(ViewExtensionDescription viewExtensionDescription) {
        EEFViewDescription eefViewDescription = EefFactory.eINSTANCE.createEEFViewDescription();
        eefViewDescription.setIdentifier(viewExtensionDescription.getIdentifier());
        eefViewDescription.setLabelExpression(viewExtensionDescription.getLabelExpression());

        List<GroupDescription> groups = viewExtensionDescription.getGroups();
        for (GroupDescription groupDescription : groups) {
            EEFGroupDescription eefGroupDescription = EefFactory.eINSTANCE.createEEFGroupDescription();
            eefViewDescription.getGroups().add(eefGroupDescription);

            eefGroupDescription.setIdentifier(groupDescription.getIdentifier());
            eefGroupDescription.setSemanticCandidateExpression(groupDescription.getSemanticCandidateExpression());
            eefGroupDescription.setLabelExpression(groupDescription.getLabelExpression());

            EEFContainerDescription eefContainerDescription = EefFactory.eINSTANCE.createEEFContainerDescription();
            eefGroupDescription.setContainer(eefContainerDescription);

            ContainerDescription containerDescription = groupDescription.getContainer();
            List<WidgetDescription> widgets = containerDescription.getWidgets();
            for (WidgetDescription widgetDescription : widgets) {
                if (widgetDescription instanceof TextDescription) {
                    TextDescription textDescription = (TextDescription) widgetDescription;

                    EEFTextDescription eefTextDescription = EefFactory.eINSTANCE.createEEFTextDescription();
                    eefContainerDescription.getWidgets().add(eefTextDescription);

                    eefTextDescription.setIdentifier(textDescription.getIdentifier());
                    eefTextDescription.setLabelExpression(textDescription.getLabelExpression());
                    eefTextDescription.setValueExpression(textDescription.getValueExpression());

                    InitialOperation initialOperation = textDescription.getInitialOperation();
                    eefTextDescription.setEditExpression("aql:self.run(selection, '" + EcoreUtil.getURI(initialOperation).toString() + "')");
                }
            }
        }

        List<PageDescription> pages = viewExtensionDescription.getPages();
        for (PageDescription pageDescription : pages) {
            EEFPageDescription eefPageDescription = EefFactory.eINSTANCE.createEEFPageDescription();
            eefViewDescription.getPages().add(eefPageDescription);

            eefPageDescription.setIdentifier(pageDescription.getIdentifier());
            eefPageDescription.setLabelExpression(pageDescription.getLabelExpression());

            // FIXME OMG THIS IS HORRIBLE!!!ELEVEN!!!
            eefPageDescription.getGroups().add(eefViewDescription.getGroups().get(0));
        }

        return eefViewDescription;
    }

}
