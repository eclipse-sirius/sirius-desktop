# Sirius Evolution Specification: Provide an easy way to move in all directions for large diagrams

## Preamble

* _Summary_:  Provide an easy way to move in all directions for large diagrams.
* _Version_:  V0.2
* _Status_: Archived
* _Date_: 2016-04-07
* _ Authors_: edugueperoux
* _Changes_: Initial version.

_Relevant tickets:_
* [Bug #491208, Provide an easy way to move in all directions for large diagrams](https://bugs.eclipse.org/bugs/show_bug.cgi?id=491208)

## Introduction

Currently to move in a large diagram to see its content, we have the outline view, the horizontal/vertical scroll, the mouse wheel to do easily vertical scroll, and the zoom.
This bugzilla is a feature request to have another way to move in large diagram, using mouse middle click, to move in all directions (horizontally and vertically).
The direction of the move is as all "new" applications: the natural scrolling (Google maps for example).

## Detailed Specification

With MacOS, this feature is natively available, for Windows and Linux, we will provide another GEF tool to manage this feature with a specific cursor representing a hand.

## Backward Compatibility and Migration Paths

### Metamodel Changes

No metamodel changes.
  
### API Changes

No API changes.

### User Interface Changes

* A new mouse shortcut will be available using mouse middle click.
* No other user interface changes.

### Documentation Changes

* This new diagram UI feature will be documented. 
* This change will appears only in release notes.

## Tests and Non-regression strategy

Manual Tests (because this is not correctly handled by SWTBot).

Test mouse move with middle click pushed:
* on diagram or on all kind of elements (node, container, list, node in container, border nodes, ...)
* with horizontal or vertically or diagonal move
* in diagram containing scroll and not
* in different zoom factor

## Implementation choices and tradeoffs
 

