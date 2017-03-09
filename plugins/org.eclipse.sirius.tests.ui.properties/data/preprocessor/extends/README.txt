This model is used to check the preprocessor behaviour and so it checks:

== View extension description ==
* The view extension description is copied and all its contained elements.

== Category ==
* The categories are copied and all their contained element.

== PageDescription ==
* Classical pages are well copied, all their features  and contained elements (page validation set, validation rules...)
* A page which extends another page by default inherits all the contained elements and all the features of the original page except the following features:
 - Extends
 - Filter Groups From Extended Page Expression	
 - Filter Validation Rules From Extended Page Expression
* A page which extends another page and redefines some features by default inherits the not redefined features and copies the redefined features
* A page referencing groups must reference the processed groups after the process operation
* A page referencing groups must referenced the processed groups after the process operation even if these groups are from another category

== PageValidation / PageSemanticValidationRule ==
* Classical pages copy the validation set rules
* Extends pages inherit the validation set rules and copy the one they defines after the inherited ones.

== GroupDescription ==
* Classical groups are well copied, all their features and contained elements (controls, group validation set, validation rules...)
* A group which extends another group by default inherits all the features of the original group except the following features:
 - Extends
 - Filter Conditional Styles From Extended Group Expression	DefaultGroup
 - Filter Controls From Extended Group Expression	
 - Filter Validation Rules From Extended Group Expression
* A group which extends another group and redefines some features by default inherits the not redefined features and copies the redefined features

== GroupValidation ==
* Classical groups copy the validation set rules
* Extends groups inherit the validation set rules and copy the one they defines after the inherited ones.

== Group Semantic Validation Rule ==
* Classical groups copy the validation set rules
* Extends groups inherit the validation set rules and copy the one they defines after the inherited ones.

== Group Property Validation Rule ==
* Classical groups copy the validation set rules
* Extends groups inherit the validation set rules and copy the one they defines after the inherited ones.
