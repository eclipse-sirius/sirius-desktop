package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.management.ToolChangeListener;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.PaletteManager;

/**
 * This listener launch an update of the attached {@link PaletteManager} when tools are updated.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public final class PaletteToolChangeListener implements ToolChangeListener {

    private PaletteManager paletteManager;

    private Diagram diagram;

    /**
     * Init the listener.
     * 
     * @param paletteManager
     *            the {@link PaletteManager} from which listening will be done.
     * @param diagram
     *            the diagram from which tool changes will be listened to.
     */
    public PaletteToolChangeListener(PaletteManager paletteManager, Diagram diagram) {
        super();
        this.paletteManager = paletteManager;
        this.diagram = diagram;
    }

    @Override
    public void notifyToolChange() {
        if (paletteManager != null && !paletteManager.isDisposed()) {
            paletteManager.update((DDiagram) diagram.getElement());
        }

    }

    @Override
    public void notifyToolChangeAfterVSMReload() {
        if (paletteManager != null && !paletteManager.isDisposed()) {
            // After the VSM has been reload, we want to clean the palette from any obsolete entry.
            paletteManager.update((DDiagram) diagram.getElement(), true);
        }

    }
}
