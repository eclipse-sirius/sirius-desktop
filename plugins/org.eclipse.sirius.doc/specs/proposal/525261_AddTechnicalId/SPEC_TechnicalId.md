
# Replace xmiid by technical id



## Reference

Affected ticket
bug: 525261 Add a technical id for elements in aird resource


## Context

Currently, for a local modeling project, the aird resource uses UUIds(org.eclipse.sirius.business.internal.resource.AirDResourceImpl.useUUIDs() returns true) for its contained elements.
In the persisted aird file, it results of of xmiid attribute for every xml elements.

Some other applications based on Sirius may serialize the aird resource differently for example in a database. In that case, AirDResourceImpl is not used but a resource which is not a XMIResourceImpl.

The consequence is when the application converts AirdResource from an implementation that not have xmiids to the AirDResourceImpl one, the xmiid are recreated and are different from the one used at the last AirDResourceImpl instanciation.

The consequence is that the aird file which has passed through the mechanism of conversion becomes different while no contained elements has changed.
That is problematic for workflow that consider aird changes. Typically for file versioning application such as git.


## Specification

### Proposal

The proposal is to have a technical id attribute (intrinsic ID) for every object in the aird resource. This technical id would be used as uuid and, as any other attributes, would be persisted whatever the aird resource implementation is used.

As a consequence, the meta-model of objects persisted in aird resource has to be changed. It covers:
* sirius meta model (viewpoint.ecore, diagram.ecore, tree.ecore, table.ecore, sequence.ecore). Actually, only viewpoint.ecore would be changed as it defined the super class of all other classes in other meta-models.
* (optional)viewpoint description metamodel (viewpoint.ecore)
* (optional)GMF meta-model (annotation.ecore)

For a first step, only Sirius meta-model is concerned to add the technical id.


### Technical

#### Requirements

Technical requirements
* The technical id must be:
** mandatory
** unique
** must not be copied when the instance is duplicated
** must not be changed
* URIs to aird element used from other application must keep being resolvable.

#### Technical thoughts

To ensure the mandatory and the uniqueness of the technical id, it could be set in the factory or in the object constructor.

In the resource loading process, when deserializing the aird xmi file, the instances are created using the Factory. Then the attributes are set.
Consequently, it is not matter if the object is initialized with a new/incorrect id because it will be erased later in the loading process.

But, on the other hand, for the old models we would like to keep the same uri fragment for objects being migrated: they could be referenced from other objects (in the same or from another resource). This might require to adapt some parts of the AirdHandler in order to be able to migrate the xmi:id attribute to some feature from the Sirius metamodel (the uid) but we will keep the value as id in the XMLResource id <=> EObject cache maps. Generally to do that, we create a migration participant that would indicate which feature to use to store the value read from the xmi:id value.

There is a special case to handle: the DRepresentation concept already has an uid which is used in DRepresentation.repPath to reference the DRepresentation. For the instances of DRepresentation the xmi:id will be kept and the previous uid id will be dropped. For example Capella still reference the DRepresentation from its RichText description properties views and it uses the xmi:id. So we need to keep the same uriFragment for objects before and after migration. This case invalidate the AirdHandler lead as we will need both old uid and xmi:id during the repPath migration. 

We will need to wait and update the resource id <=> EObject cache in the postLoad phase in order to let the load correctly do the id ref resolution (which is currently done very late for performance reasons, see OPTION_DEFER_IDREF_RESOLUTION) and keep the possibility to call DRepresentationDescriptor.getRepresentation(true). 

We will also need to test that the resolution of the GMF Diagram.element reference is still working after migration.

AirdResource

AirdResource is the resource corresponding to the aird file and AirdResourceImpl is the implementation inheriting from XMIResourceImpl.
As AirdResource will keep containing GMF element that will keep having their xmiid as unique identifier, the method *AirdResourceImpl.useUUIDs* will keep returning *true*.

The uri will keep being based on xmiids extrinsic id for every elements which are not instances of IdentifiedElement.
There will be no xmi:id in the serialization for instances of IdentifiedElement (See javadoc of org.eclipse.emf.ecore.xmi.XMLHelper.getID(EObject eObject): return id or null to suppress).

To ensure the uniqueness, when the element is copied:
* if org.eclipse.emf.ecore.util.EcoreUtil.Copier is used, a check is done on EStructuralFeature.isChangeble but only if the EAttribute is a FeatureMap (FeatureMapUtil.isFeatureMap(eAttribute));
* else the applicative code had to ensure that the id attribute is not copied on another instance.
In conclusion, we can not rely on changeable state and consider that it is up to the applicative code to set the right uid to the copied element. 


#### Documentation

https://fr.slideshare.net/kenn.hussey/performance-and-extensibility-with-emf
https://www.eclipse.org/forums/index.php?t=msg&th=130100/
https://www.eclipse.org/forums/index.php/t/127193/


### Changes to do 

#### Where to add the technical id

A base Class *IdentifiedElement* is added that will be implemented by all Sirius model element. To mutualize code, there should be *IdentifiedElement* API and *IdentifiedElementImpl* abstract that should be inherited by all top level implementation of Sirius model Element.
In viewpoint.ecore model, *IdentifiedElement* should be have _Abstract=true_ and _Interface=false_

In *IdentifiedElementImpl* constructor, the uiid is set using EcoreUtil.generateUUID()

Warning: For the copied EObjects, applicative code must ensure that a different uid is set compared to the original element.

Classes that should inherit from *IdentifiedElement*
* viewpoint

DAnalysis
DFeatureExtension
DSemanticDecorator
DRepresentationDescriptor->DModelElement
DocumentedElement
DRepresentationElement
DView
MetaModelExtension
Decoration
DAnalysisCustomData
Customizable
Style
DFile
DResourceContainer
UIState (transient)

* diagram

GraphicalFilter (that has been set interface=false)
(HideFilter)
(HideLabelFilter)
(FoldingPointFilter)
(FoldingFilter)
(AppliedCompositeFilters)
(AbsoluteBoundsFilter)
EdgeTarget
FilterVariableHistory
ComputedStyleDescriptionRegistry
DragAndDropTarget
VariableValue

* table, tree

DTableElementStyle
DTreeElementSynchronizer

* sequence

EventEndsOrdering
EventEnd
InstanceRolesOrdering
TTransformer
(TSequenceDiagram inherits now from TTransformer first)
TMessageExtremity


### AirdResource


The method *AirdResourceImpl.useUUIDs* will keep returning *true*.

The *attachedHelper* method is overridden to initialize use the uid in the id <=> EObject cache. It corresponds when attaching a new element or creating AirdResource from another resource(CDO for example)

The uri/urifragment of IdentifiedElement instances now uses the uid.


#### Migration

For old model, a participant will change the uid of all IdentifiedElement to be equal to xmi:id.
The DRepresentationDescription.repPath needs migration. 
The GMF Diagram.element href should continue to work fine as we will conserve the same urifragment for DRepresentations (and other IdentifiedElements).

