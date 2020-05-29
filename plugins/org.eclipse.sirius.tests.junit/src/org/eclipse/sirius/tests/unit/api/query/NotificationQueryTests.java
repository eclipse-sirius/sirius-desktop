/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.query;

import java.io.ByteArrayInputStream;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.common.tools.api.query.NotificationQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

/**
 * Test class for {@link NotificationQuery}.
 * This test was created for bug 563730
 * 
 * @author <a href="mailto:stephane.thibaudeau@obeo.fr">Stéphane Thibaudeau</a>
 */
@SuppressWarnings("restriction")
public class NotificationQueryTests extends SiriusDiagramTestCase {

	private static final String EXTENSION_ID = "NotificationQueryTests";
	
	private static final String EPACKAGE_META_DATA_EXTENSION_POINT = "org.eclipse.sirius.common.package_meta_data";

	/** Dynamic metamodel */
	private EPackage mm_pkg;
	private EClass mm_rootEClass;
	private EReference mm_childrenERef;
	private EClass mm_childEClass;
	private EAttribute mm_nameAtt;

	/** model */
	private EObject model_rootObject;
	private EObject model_childObject;
	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        initDynamicMetamodel();
        initModel();
    }

    /**
     * Test the case of a EPackage with a EClass with null as name.
     */
    public void testNotificationQuery() {
    	Notification notification = new ENotificationImpl((InternalEObject)model_childObject, Notification.SET, mm_nameAtt, "ChildObject", "newName");
    	
    	NotificationQuery query = new NotificationQuery(notification);
    	
    	// initial state
    	assertEquals("Notification should not be transient", false, query.isTransientNotification());
    	
    	// Set the containment reference to be transient
    	mm_childrenERef.setTransient(true);
    	assertEquals("Notification should be transient", true, query.isTransientNotification());
    	
    	// Register the extension, this extension indicates that rootEClass corresponds to a document root
    	// now our Root class should be seen as a DocumentRoot
    	registerExtensionAndExecute(() -> assertEquals("Notification should not be transient", false, query.isTransientNotification()));
    	
    	// Remove the extension
    	removeExtensionAndExecute(() -> assertEquals("Notification should be transient", true, query.isTransientNotification()));
    }
    
    @SuppressWarnings("unchecked")
	private void initModel() {
    	EFactory factory = mm_pkg.getEFactoryInstance();
    	
    	model_childObject = factory.create(mm_childEClass);
    	model_childObject.eSet(mm_nameAtt, "ChildObject");
    	
    	model_rootObject = factory.create(mm_rootEClass);
    	((EList<EObject>)model_rootObject.eGet(mm_childrenERef)).add(model_childObject);
    }
    
    private void initDynamicMetamodel() {
    	EcoreFactory factory = EcoreFactory.eINSTANCE;
    	
    	mm_childEClass = factory.createEClass();
    	mm_childEClass.setName("Child");
    	
    	mm_nameAtt = factory.createEAttribute();
    	mm_nameAtt.setName("name");
    	mm_nameAtt.setEType(EcorePackage.Literals.ESTRING);
    	mm_childEClass.getEStructuralFeatures().add(mm_nameAtt);

    	mm_rootEClass = factory.createEClass();
		mm_rootEClass.setName("Root");

		mm_childrenERef = factory.createEReference();
		mm_childrenERef.setName("children");
		mm_childrenERef.setEType(mm_childEClass);
		mm_childrenERef.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
		mm_childrenERef.setContainment(true);
		mm_rootEClass.getEStructuralFeatures().add(mm_childrenERef);
		
		mm_pkg = factory.createEPackage();
		mm_pkg.setName("testMM");
		mm_pkg.setNsPrefix("testMM");
		mm_pkg.setNsURI("http://www.example.com/testMM");
		
		mm_pkg.getEClassifiers().add(mm_rootEClass);
		mm_pkg.getEClassifiers().add(mm_childEClass);
		
    }
    
    /**
     * Installs dynamically an extension that adds "custom" at the end of the value of the "name" field CellEditor.
     */
    private void registerExtensionAndExecute(Runnable executeAfterModification) {
        IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
        
        // Add a listener to execute runnable after modification has been done
        IRegistryEventListener listener = new IRegistryEventListener() {
			
			@Override
			public void removed(IExtensionPoint[] extensionPoints) {
			}
			
			@Override
			public void removed(IExtension[] extensions) {				
			}
			
			@Override
			public void added(IExtensionPoint[] extensionPoints) {
			}
			
			@Override
			public void added(IExtension[] extensions) {
				executeAfterModification.run();
				extensionRegistry.removeListener(this);
			}
		};
		extensionRegistry.addListener(listener, EPACKAGE_META_DATA_EXTENSION_POINT);
        
        IContributor contributor = ContributorFactoryOSGi.createContributor(SiriusTestsPlugin.getDefault().getBundle());
        extensionRegistry.addContribution(new ByteArrayInputStream(getPluginXml().getBytes()), contributor, false, null, null, ((ExtensionRegistry) extensionRegistry).getTemporaryUserToken());
    }
    
    private String getPluginXml() {
    	return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<?eclipse version=\"3.4\"?>" +
				"<plugin>" +
				   "<extension id=\"" + EXTENSION_ID + "\" point=\"org.eclipse.sirius.common.package_meta_data\">" +
				      "<ePackageMetaData nsURI=\"" + mm_pkg.getNsURI() + "\"><documentRoot className=\"" + mm_rootEClass.getName() + "\"/></ePackageMetaData>" +
				   "</extension>" +
				"</plugin>";
    }

    /**
     * Remove the installed extension.
     */
    private void removeExtensionAndExecute(Runnable executeAfterModification) {
        IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
        
        // Add a listener to execute runnable after modification has been done
        IRegistryEventListener listener = new IRegistryEventListener() {
			
			@Override
			public void removed(IExtensionPoint[] extensionPoints) {
			}
			
			@Override
			public void removed(IExtension[] extensions) {				
				executeAfterModification.run();
				extensionRegistry.removeListener(this);
			}
			
			@Override
			public void added(IExtensionPoint[] extensionPoints) {
			}
			
			@Override
			public void added(IExtension[] extensions) {
			}
		};
		extensionRegistry.addListener(listener, EPACKAGE_META_DATA_EXTENSION_POINT);
        
        IExtension extension = extensionRegistry.getExtension(EPACKAGE_META_DATA_EXTENSION_POINT, SiriusTestsPlugin.PLUGIN_ID + "." + EXTENSION_ID);
        extensionRegistry.removeExtension(extension, ((ExtensionRegistry) extensionRegistry).getTemporaryUserToken());
    }

}
