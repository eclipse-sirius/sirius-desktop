/*******************************************************************************
 * Copyright (c) 2011, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EcoreIntrinsicExtender;
import org.eclipse.sirius.synchronizer.SemanticPartitionInvalidator;
import org.eclipse.sirius.tests.data.GroupRefreshTreeOdesignData;
import org.eclipse.sirius.tests.data.TreeDescriptionGenericEMFTree;
import org.eclipse.sirius.tests.unit.common.TreeCommonTest;
import org.eclipse.sirius.tests.unit.common.TreeEcoreModeler;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.TreeFactory;
import org.eclipse.sirius.tree.business.api.interaction.DTreeUserInteraction;
import org.eclipse.sirius.tree.business.internal.dialect.common.tree.DTreeRefresh;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.junit.Assert;
import org.junit.Before;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

public class TreeRefreshTests extends TreeCommonTest implements EcoreModeler, TreeEcoreModeler {

    private GlobalContext ctx;

    private SemanticPartitionInvalidator invalidator;

    private GroupRefreshTreeOdesignData odesign;

    private Map<DRepresentation, String> repToName;

    @Override
    @Before
    public void setUp() throws Exception {
        invalidator = new SemanticPartitionInvalidator();

        odesign = new GroupRefreshTreeOdesignData();
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            odesign.loadFromPlugin();
        } else {
            odesign.createStandaloneInstance();
        }

        ResourceSet set = new ResourceSetImpl();

        ModelAccessor accessor = new ModelAccessor();
        accessor.addExtender(new EcoreIntrinsicExtender(), ExtenderConstants.HIGHEST_PRIORITY);
        accessor.init(set);

        IInterpreter interpreter = CompoundInterpreter.createGenericInterpreter();

        ctx = new GlobalContext(accessor, interpreter, null);

        platformProblemsListener.initLoggers();
        platformProblemsListener.setWarningCatchActive(true);
        repToName = new HashMap<>();

    }

    /**
     * Tests that warnings occurring during semantic candidate expression evaluation on a TreeRefresh are logged in
     * error log.
     * 
     */
    public void testWarningLoggingWithIncorrectSemanticCandidateExpression() {
        EPackage semanticModel = EcoreFactory.eINSTANCE.createEPackage();
        semanticModel.setName("testModel");

        DTree newTree = TreeFactory.eINSTANCE.createDTree();
        newTree.setTarget(semanticModel);
        newTree.setDescription(odesign.group().design().epackagecontent().object());
        List<TreeItemMapping> mappings = Lists.newArrayList(Iterators.filter(odesign.group().design().epackagecontent().object().eAllContents(), TreeItemMapping.class));

        Assert.assertFalse(platformProblemsListener.doesAWarningOccurs());
        mappings.get(0).setSemanticCandidatesExpression("aql:incorrectExpression");

        DTreeRefresh refresher = new DTreeRefresh(newTree, mappings, invalidator, ctx);
        refresher.refresh(true, new NullProgressMonitor());
        Assert.assertEquals(1, platformProblemsListener.warningsCount());
        Assert.assertTrue(platformProblemsListener.getWarnings().get("org.eclipse.core.runtime").iterator().next().getException() instanceof EvaluationException);
        platformProblemsListener.clearWarnings();

    }

    public void testOrderMatchesTheModelOrder() throws Exception {

        EPackage semanticModel = EcoreUtil.copy(EcorePackage.eINSTANCE);
        TreeDescriptionGenericEMFTree representation = odesign.group().design().genericemftree();
        DTree firstTree = TreeFactory.eINSTANCE.createDTree();
        firstTree.setTarget(semanticModel);
        firstTree.setDescription(representation.object());

        new DTreeUserInteraction(firstTree, ctx).refreshContent(new NullProgressMonitor());

        Iterator<DTreeItem> itTree = firstTree.getOwnedTreeItems().iterator();
        Iterator<EObject> itSemContent = semanticModel.eContents().iterator();
        while (itSemContent.hasNext()) {
            EObject semantic = itSemContent.next();
            Assert.assertSame(semantic, itTree.next().getTarget());
        }

    }

    public void testSwitchMappingInHierarchy() throws Exception {

        EPackage semanticModel = EcoreFactory.eINSTANCE.createEPackage();
        semanticModel.setName("testModel");
        EClass namedElement = EcoreFactory.eINSTANCE.createEClass();
        namedElement.setName("ENamedElement");
        namedElement.setAbstract(true);
        semanticModel.getEClassifiers().add(namedElement);

        DTree newTree = TreeFactory.eINSTANCE.createDTree();
        repToName.put(newTree, "(*)");

        newTree.setTarget(semanticModel);
        newTree.setDescription(odesign.group().design().epackagecontent().object());
        List<TreeItemMapping> mappings = Lists.newArrayList(Iterators.filter(odesign.group().design().epackagecontent().object().eAllContents(), TreeItemMapping.class));

        DTreeRefresh refresher = new DTreeRefresh(newTree, mappings, invalidator, ctx);
        refresher.refresh(new NullProgressMonitor());

        String before = toString(newTree);
        // @formatter:off
        Assert.assertEquals("\n" + "|-(*)\n" + "  |-*ENamedElement", before);

        Assert.assertTrue(before.indexOf("*ENamedElement") != -1);

        ((EClass) semanticModel.getEClassifier("ENamedElement")).setAbstract(false);

        refresher.refresh(new NullProgressMonitor());

        String after = toString(newTree);

        Assert.assertFalse(after.indexOf("*ENamedElement") != -1);

        Assert.assertEquals("\n" + "|-(*)\n" + "  |-ENamedElement", after);

        // @formatter:on
    }

    public void testOrderKeptOnSubsequentRefreshes() throws Exception {

        EPackage semanticModel = EcoreUtil.copy(EcorePackage.eINSTANCE);
        DTree newTree = TreeFactory.eINSTANCE.createDTree();
        repToName.put(newTree, "ENamedElement");
        newTree.setTarget(semanticModel);
        newTree.setDescription(odesign.group().design().epackagecontent().object());

        DTreeUserInteraction interaction = new DTreeUserInteraction(newTree, ctx);
        interaction.refreshContent(new NullProgressMonitor()).expandAll();

        String before = toString(newTree);

        interaction.refreshContent(new NullProgressMonitor());

        String after = toString(newTree);
        Assert.assertEquals(before, after);
    }

    public void testDeleteElement() throws Exception {

        EPackage semanticModel = EcoreUtil.copy(EcorePackage.eINSTANCE);
        DTree newTree = TreeFactory.eINSTANCE.createDTree();
        repToName.put(newTree, "ENamedElement");
        newTree.setTarget(semanticModel);
        newTree.setDescription(odesign.group().design().epackagecontent().object());
        List<TreeItemMapping> mappings = Lists.newArrayList(Iterators.filter(odesign.group().design().epackagecontent().object().eAllContents(), TreeItemMapping.class));

        DTreeRefresh refresher = new DTreeRefresh(newTree, mappings, invalidator, ctx);
        refresher.refresh(new NullProgressMonitor());

        EcoreUtil.delete(semanticModel.getEClassifier("EModelElement"));

        refresher.refresh(new NullProgressMonitor());

        String afterDelete = toString(newTree);

        Assert.assertFalse(afterDelete.indexOf("EModelElement") != -1);
    }

    public void testCreateElement() throws Exception {

        EPackage semanticModel = EcoreUtil.copy(EcorePackage.eINSTANCE);
        DTree newTree = TreeFactory.eINSTANCE.createDTree();
        repToName.put(newTree, "ENamedElement");
        newTree.setTarget(semanticModel);
        newTree.setDescription(odesign.group().design().epackagecontent().object());
        List<TreeItemMapping> mappings = Lists.newArrayList(Iterators.filter(odesign.group().design().epackagecontent().object().eAllContents(), TreeItemMapping.class));

        DTreeRefresh refresher = new DTreeRefresh(newTree, mappings, invalidator, ctx);
        refresher.refresh(new NullProgressMonitor());

        String initialState = toString(newTree);
        String[] tabInitialState = initialState.split("\n");
        int numberOfEModelElement = 0;
        for (int i = 0; i <= tabInitialState.length - 1; i++) {
            if (tabInitialState[i].equals("  |-*EModelElement")) {
                numberOfEModelElement++;
            }
        }
        assertEquals("We should have only one Abstract EModelElement instance.", 1, numberOfEModelElement);

        semanticModel.getEClassifiers().add(EcoreUtil.copy(semanticModel.getEClassifier("EModelElement")));

        refresher.refresh(new NullProgressMonitor());

        String afterCreate = toString(newTree);

        String[] tabAfterCreate = afterCreate.split("\n");
        int numberOfEModelElementAfterDuplication = 0;
        for (int i = 0; i <= tabAfterCreate.length - 1; i++) {
            if (tabAfterCreate[i].equals("  |-*EModelElement")) {
                numberOfEModelElementAfterDuplication++;
            }
        }
        assertEquals("We should have one more Abstract EModelElement instance after duplication.", numberOfEModelElement + 1, numberOfEModelElementAfterDuplication);
    }

    public void testRenameElement() throws Exception {
        EPackage semanticModel = EcoreUtil.copy(EcorePackage.eINSTANCE);
        DTree newTree = TreeFactory.eINSTANCE.createDTree();
        repToName.put(newTree, "ENamedElement");
        newTree.setTarget(semanticModel);
        newTree.setDescription(odesign.group().design().epackagecontent().object());
        List<TreeItemMapping> mappings = Lists.newArrayList(Iterators.filter(odesign.group().design().epackagecontent().object().eAllContents(), TreeItemMapping.class));

        DTreeRefresh refresher = new DTreeRefresh(newTree, mappings, invalidator, ctx);
        refresher.refresh(new NullProgressMonitor());

        semanticModel.getEClassifier("EModelElement").setName("RenamedElement");

        refresher.refresh(new NullProgressMonitor());

        String afterRename = toString(newTree);

        Assert.assertFalse(afterRename.indexOf("EModelElement") != -1);
    }

    public void testRefreshBoundedToExpansionState() throws Exception {
        EPackage semanticModel = EcoreUtil.copy(EcorePackage.eINSTANCE);
        DTree newTree = TreeFactory.eINSTANCE.createDTree();
        newTree.setTarget(semanticModel);
        TreeDescription specification = odesign.group().design().genericemftree().object();
        newTree.setDescription(specification);

        List<TreeItemMapping> mappings = Lists.newArrayList(Iterators.filter(specification.eAllContents(), TreeItemMapping.class));

        DTreeRefresh refresher = new DTreeRefresh(newTree, mappings, invalidator, ctx);
        refresher.refresh(new NullProgressMonitor());

        newTree.getOwnedTreeItems().get(0).setExpanded(true);

        refresher.refresh(new NullProgressMonitor());

        newTree.getOwnedTreeItems().get(0).getOwnedTreeItems().get(0).setExpanded(true);

        refresher.refresh(new NullProgressMonitor());
    }

    class ToStringueur {

        private DTreeItemContainer current;

        private String label;

        private int index;

        public ToStringueur(DTreeItemContainer current, int currentIndex) {
            this.current = current;
            this.index = currentIndex;
            if (current instanceof DTree) {
                this.label = repToName.get(current);
            } else if (current instanceof DTreeItem) {
                this.label = ((DTreeItem) current).getName();
            }
            if (StringUtil.isEmpty(this.label)) {
                this.label = "(*)";
            }
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("\n");
            for (int i = 0; i < index; i++) {
                builder.append(" ");
            }
            builder.append("|-");
            builder.append(label);
            int newIndex = index + 2;
            if (current instanceof DTreeItem) {
                if (((DTreeItem) current).isExpanded()) {
                    generateChildrenText(builder, newIndex);
                }
            } else if (current instanceof DTree) {
                generateChildrenText(builder, newIndex);
            }
            return builder.toString();

        }

        private void generateChildrenText(StringBuilder builder, int newIndex) {
            for (DTreeItem item : current.getOwnedTreeItems()) {
                builder.append(new ToStringueur(item, newIndex));
            }
        }
    }

    private String toString(DTreeItemContainer newTree) {
        return new ToStringueur(newTree, 0).toString();
    }

    /**
     * Tests that reordering semantic elements also reorders elements in the Sirius {@link DTree}. See 481433.
     * 
     */
    public void testSynchronizationSemanticElementsMappgings() {

        EPackage semanticModel = (EPackage) EcoreUtil.create(EcorePackage.eINSTANCE.getEPackage());
        EClass subClass1 = (EClass) EcoreUtil.create(EcorePackage.eINSTANCE.getEClass());
        subClass1.setName("EClass1");

        EClass subClass2 = (EClass) EcoreUtil.create(EcorePackage.eINSTANCE.getEClass());
        subClass2.setName("EClass2");
        semanticModel.getEClassifiers().add(subClass1);
        semanticModel.getEClassifiers().add(subClass2);

        DTree newTree = TreeFactory.eINSTANCE.createDTree();
        newTree.setTarget(semanticModel);
        newTree.setDescription(odesign.group().design().genericemftree().object());
        List<TreeItemMapping> mappings = Lists.newArrayList(Iterators.filter(odesign.group().design().genericemftree().object().eAllContents(), TreeItemMapping.class));

        DTreeRefresh refresher = new DTreeRefresh(newTree, mappings, invalidator, ctx);
        refresher.refresh(new NullProgressMonitor());

        EList<DTreeItem> ownedTreeItems = newTree.getOwnedTreeItems();
        assertEquals("EClass1", ownedTreeItems.get(0).getName());
        assertEquals("EClass2", ownedTreeItems.get(1).getName());

        semanticModel.getEClassifiers().move(0, 1);
        refresher.refresh(new NullProgressMonitor());

        assertEquals("EClass2", ownedTreeItems.get(0).getName());
        assertEquals("EClass1", ownedTreeItems.get(1).getName());
    }

    /**
     * This test works on my computer (LRE) but not on Hudson. TODO : Fix this test on Hudson.
     * 
     * @throws Exception
     */
    public void _testGnericEMFTreeCollapseAll() throws Exception {

        EPackage semanticModel = EcoreUtil.copy(EcorePackage.eINSTANCE);
        DTree newTree = TreeFactory.eINSTANCE.createDTree();
        newTree.setTarget(semanticModel);
        TreeDescriptionGenericEMFTree representation = odesign.group().design().genericemftree();
        newTree.setDescription(representation.object());

        new DTreeUserInteraction(newTree, ctx).refreshContent(new NullProgressMonitor()).expandAll();

        String after = toString(newTree);
        Assert.assertEquals(COMPLETE_ECORE_AS_STRING, after);
    }

    public void testOrderIsDeterministicAmongRepresentationInstances() throws Exception {

        EPackage semanticModel = EcoreUtil.copy(EcorePackage.eINSTANCE);
        TreeDescriptionGenericEMFTree representation = odesign.group().design().genericemftree();
        DTree firstTree = TreeFactory.eINSTANCE.createDTree();
        firstTree.setTarget(semanticModel);
        firstTree.setDescription(representation.object());

        DTree secondTree = TreeFactory.eINSTANCE.createDTree();
        secondTree.setTarget(semanticModel);
        secondTree.setDescription(representation.object());

        new DTreeUserInteraction(firstTree, ctx).refreshContent(new NullProgressMonitor()).expand();
        new DTreeUserInteraction(secondTree, ctx).refreshContent(new NullProgressMonitor()).expand();

        String first = toString(firstTree);
        String after = toString(secondTree);

        Assert.assertEquals(first, after);
    }

    // @formatter:off
    static final String COMPLETE_ECORE_AS_STRING = "\n" + "|-(*)\n" + "  |-EAttribute\n" + "    |-iD\n" + "      |-(*)\n" + "    |-eAttributeType\n" + "      |-(*)\n" + "    |-(*)\n"
            + "  |-EAnnotation\n" + "    |-source\n" + "      |-(*)\n" + "    |-details\n" + "      |-(*)\n" + "    |-eModelElement\n" + "      |-(*)\n" + "    |-contents\n" + "      |-(*)\n"
            + "    |-references\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-EClass\n" + "    |-isSuperTypeOf\n" + "      |-(*)\n" + "      |-someClass\n" + "        |-(*)\n"
            + "    |-getFeatureCount\n" + "      |-(*)\n" + "    |-getEStructuralFeature\n" + "      |-(*)\n" + "      |-featureID\n" + "        |-(*)\n" + "    |-getFeatureID\n" + "      |-(*)\n"
            + "      |-feature\n" + "        |-(*)\n" + "    |-getEStructuralFeature\n" + "      |-(*)\n" + "      |-featureName\n" + "        |-(*)\n" + "    |-abstract\n" + "      |-(*)\n"
            + "    |-interface\n" + "      |-(*)\n" + "    |-eSuperTypes\n" + "      |-(*)\n" + "    |-eOperations\n" + "      |-(*)\n" + "    |-eAllAttributes\n" + "      |-(*)\n"
            + "    |-eAllReferences\n" + "      |-(*)\n" + "    |-eReferences\n" + "      |-(*)\n" + "    |-eAttributes\n" + "      |-(*)\n" + "    |-eAllContainments\n" + "      |-(*)\n"
            + "    |-eAllOperations\n" + "      |-(*)\n" + "    |-eAllStructuralFeatures\n" + "      |-(*)\n" + "    |-eAllSuperTypes\n" + "      |-(*)\n" + "    |-eIDAttribute\n" + "      |-(*)\n"
            + "    |-eStructuralFeatures\n" + "      |-(*)\n" + "    |-eGenericSuperTypes\n" + "      |-(*)\n" + "    |-eAllGenericSuperTypes\n" + "      |-(*)\n" + "    |-(*)\n"
            + "  |-EClassifier\n" + "    |-isInstance\n" + "      |-(*)\n" + "      |-object\n" + "        |-(*)\n" + "    |-getClassifierID\n" + "      |-(*)\n" + "    |-instanceClassName\n"
            + "      |-(*)\n" + "    |-instanceClass\n" + "      |-(*)\n" + "        |-(*)\n" + "    |-defaultValue\n" + "      |-(*)\n" + "    |-instanceTypeName\n" + "      |-(*)\n"
            + "    |-ePackage\n" + "      |-(*)\n" + "    |-eTypeParameters\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-EDataType\n" + "    |-serializable\n" + "      |-(*)\n" + "    |-(*)\n"
            + "  |-EEnum\n" + "    |-getEEnumLiteral\n" + "      |-(*)\n" + "      |-name\n" + "        |-(*)\n" + "    |-getEEnumLiteral\n" + "      |-(*)\n" + "      |-value\n" + "        |-(*)\n"
            + "    |-getEEnumLiteralByLiteral\n" + "      |-(*)\n" + "      |-literal\n" + "        |-(*)\n" + "    |-eLiterals\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-EEnumLiteral\n"
            + "    |-value\n" + "      |-(*)\n" + "    |-instance\n" + "      |-(*)\n" + "    |-literal\n" + "      |-(*)\n" + "    |-eEnum\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-EFactory\n"
            + "    |-create\n" + "      |-(*)\n" + "      |-eClass\n" + "        |-(*)\n" + "    |-createFromString\n" + "      |-(*)\n" + "      |-eDataType\n" + "        |-(*)\n"
            + "      |-literalValue\n" + "        |-(*)\n" + "    |-convertToString\n" + "      |-(*)\n" + "      |-eDataType\n" + "        |-(*)\n" + "      |-instanceValue\n" + "        |-(*)\n"
            + "    |-ePackage\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-EModelElement\n" + "    |-getEAnnotation\n" + "      |-(*)\n" + "      |-source\n" + "        |-(*)\n"
            + "    |-eAnnotations\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-ENamedElement\n" + "    |-name\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-EObject\n" + "    |-eClass\n"
            + "      |-(*)\n" + "    |-eIsProxy\n" + "      |-(*)\n" + "    |-eResource\n" + "      |-(*)\n" + "    |-eContainer\n" + "      |-(*)\n" + "    |-eContainingFeature\n" + "      |-(*)\n"
            + "    |-eContainmentFeature\n" + "      |-(*)\n" + "    |-eContents\n" + "      |-(*)\n" + "        |-(*)\n" + "    |-eAllContents\n" + "      |-(*)\n" + "        |-(*)\n"
            + "    |-eCrossReferences\n" + "      |-(*)\n" + "        |-(*)\n" + "    |-eGet\n" + "      |-(*)\n" + "      |-feature\n" + "        |-(*)\n" + "    |-eGet\n" + "      |-(*)\n"
            + "      |-feature\n" + "        |-(*)\n" + "      |-resolve\n" + "        |-(*)\n" + "    |-eSet\n" + "      |-feature\n" + "        |-(*)\n" + "      |-newValue\n" + "        |-(*)\n"
            + "    |-eIsSet\n" + "      |-(*)\n" + "      |-feature\n" + "        |-(*)\n" + "    |-eUnset\n" + "      |-feature\n" + "        |-(*)\n" + "  |-EOperation\n"
            + "    |-eContainingClass\n" + "      |-(*)\n" + "    |-eTypeParameters\n" + "      |-(*)\n" + "    |-eParameters\n" + "      |-(*)\n" + "    |-eExceptions\n" + "      |-(*)\n"
            + "    |-eGenericExceptions\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-EPackage\n" + "    |-getEClassifier\n" + "      |-(*)\n" + "      |-name\n" + "        |-(*)\n" + "    |-nsURI\n"
            + "      |-(*)\n" + "    |-nsPrefix\n" + "      |-(*)\n" + "    |-eFactoryInstance\n" + "      |-(*)\n" + "    |-eClassifiers\n" + "      |-(*)\n" + "    |-eSubpackages\n"
            + "      |-(*)\n" + "    |-eSuperPackage\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-EParameter\n" + "    |-eOperation\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-EReference\n"
            + "    |-containment\n" + "      |-(*)\n" + "    |-container\n" + "      |-(*)\n" + "    |-resolveProxies\n" + "      |-(*)\n" + "    |-eOpposite\n" + "      |-(*)\n"
            + "    |-eReferenceType\n" + "      |-(*)\n" + "    |-eKeys\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-EStructuralFeature\n" + "    |-getFeatureID\n" + "      |-(*)\n"
            + "    |-getContainerClass\n" + "      |-(*)\n" + "        |-(*)\n" + "    |-changeable\n" + "      |-(*)\n" + "    |-volatile\n" + "      |-(*)\n" + "    |-transient\n" + "      |-(*)\n"
            + "    |-defaultValueLiteral\n" + "      |-(*)\n" + "    |-defaultValue\n" + "      |-(*)\n" + "    |-unsettable\n" + "      |-(*)\n" + "    |-derived\n" + "      |-(*)\n"
            + "    |-eContainingClass\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-ETypedElement\n" + "    |-ordered\n" + "      |-(*)\n" + "    |-unique\n" + "      |-(*)\n" + "    |-lowerBound\n"
            + "      |-(*)\n" + "    |-upperBound\n" + "      |-(*)\n" + "    |-many\n" + "      |-(*)\n" + "    |-required\n" + "      |-(*)\n" + "    |-eType\n" + "      |-(*)\n"
            + "    |-eGenericType\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-EStringToStringMapEntry\n" + "    |-key\n" + "      |-(*)\n" + "    |-value\n" + "      |-(*)\n" + "  |-EGenericType\n"
            + "    |-eUpperBound\n" + "      |-(*)\n" + "    |-eTypeArguments\n" + "      |-(*)\n" + "    |-eRawType\n" + "      |-(*)\n" + "    |-eLowerBound\n" + "      |-(*)\n"
            + "    |-eTypeParameter\n" + "      |-(*)\n" + "    |-eClassifier\n" + "      |-(*)\n" + "    |-(*)\n" + "  |-ETypeParameter\n" + "    |-eBounds\n" + "      |-(*)\n" + "    |-(*)\n"
            + "  |-EBigDecimal\n" + "    |-(*)\n" + "      |-(*)\n" + "  |-EBigInteger\n" + "    |-(*)\n" + "      |-(*)\n" + "  |-EBoolean\n" + "    |-(*)\n" + "      |-(*)\n"
            + "  |-EBooleanObject\n" + "    |-(*)\n" + "      |-(*)\n" + "      |-(*)\n" + "  |-EByte\n" + "    |-(*)\n" + "      |-(*)\n" + "  |-EByteArray\n" + "    |-(*)\n" + "      |-(*)\n"
            + "  |-EByteObject\n" + "    |-(*)\n" + "      |-(*)\n" + "      |-(*)\n" + "  |-EChar\n" + "  |-ECharacterObject\n" + "    |-(*)\n" + "      |-(*)\n" + "      |-(*)\n" + "  |-EDate\n"
            + "  |-EDiagnosticChain\n" + "  |-EDouble\n" + "    |-(*)\n" + "      |-(*)\n" + "  |-EDoubleObject\n" + "    |-(*)\n" + "      |-(*)\n" + "      |-(*)\n" + "  |-EEList\n" + "    |-E\n"
            + "  |-EEnumerator\n" + "  |-EFeatureMap\n" + "  |-EFeatureMapEntry\n" + "  |-EFloat\n" + "    |-(*)\n" + "      |-(*)\n" + "  |-EFloatObject\n" + "    |-(*)\n" + "      |-(*)\n"
            + "      |-(*)\n" + "  |-EInt\n" + "    |-(*)\n" + "      |-(*)\n" + "  |-EIntegerObject\n" + "    |-(*)\n" + "      |-(*)\n" + "      |-(*)\n" + "  |-EJavaClass\n" + "    |-T\n"
            + "  |-EJavaObject\n" + "  |-ELong\n" + "    |-(*)\n" + "      |-(*)\n" + "  |-ELongObject\n" + "    |-(*)\n" + "      |-(*)\n" + "      |-(*)\n" + "  |-EMap\n" + "    |-K\n"
            + "    |-V\n" + "  |-EResource\n" + "  |-EResourceSet\n" + "  |-EShort\n" + "    |-(*)\n" + "      |-(*)\n" + "  |-EShortObject\n" + "    |-(*)\n" + "      |-(*)\n" + "      |-(*)\n"
            + "  |-EString\n" + "    |-(*)\n" + "      |-(*)\n" + "  |-ETreeIterator\n" + "    |-E";
}
