package org.eclipse.sirius.ui.properties.internal;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

public class SiriusToolServices {
    public void run(EObject eObject, Object selection, String initialCommandUri) {
        DRepresentation representation = null;

        EObject semanticElement = null;
        if (selection instanceof GraphicalEditPart) {
            semanticElement = ((GraphicalEditPart) selection).resolveSemanticElement();
        } else if (selection instanceof ConnectionEditPart) {
            semanticElement = ((Edge) ((ConnectionEditPart) selection).getModel()).getElement();
        } else if (selection instanceof DSemanticDecorator) {
            semanticElement = ((DSemanticDecorator) selection).getTarget();
        }
        if (semanticElement instanceof DSemanticDecorator) {
            DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) semanticElement;
            EObjectQuery eObjectQuery = new EObjectQuery(dSemanticDecorator);
            org.eclipse.sirius.ext.base.Option<DRepresentation> oRepresentation = eObjectQuery.getRepresentation();
            if (oRepresentation.some()) {
                representation = oRepresentation.get();
            }
        }

        Session session = new EObjectQuery(eObject).getSession();

        ModelAccessor modelAccessor = session.getModelAccessor();
        TaskHelper taskHelper = new TaskHelper(modelAccessor, new NoUICallback());

        ModelOperation modelOperation = null;

        Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(true);
        for (Viewpoint viewpoint : selectedViewpoints) {
            Resource eResource = viewpoint.eResource();
            if (eResource != null && URI.createURI(initialCommandUri).trimFragment().equals(eResource.getURI())) {
                EObject modelOperationEObject = eResource.getEObject(URI.createURI(initialCommandUri).fragment());
                if (modelOperationEObject instanceof InitialOperation) {
                    modelOperation = ((InitialOperation) modelOperationEObject).getFirstModelOperations();
                }
            }
        }

        if (representation != null && modelOperation != null) {
            ICommandTask task = taskHelper.buildTaskFromModelOperation(representation, eObject, modelOperation);
            SiriusCommand command = new SiriusCommand(session.getTransactionalEditingDomain(), "SiriusToolServices#run");
            command.getTasks().add(task);
            session.getTransactionalEditingDomain().getCommandStack().execute(command);
        }
    }
}
