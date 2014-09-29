<%
metamodel http://www.eclipse.org/emf/2002/Ecore

import org.eclipse.sirius.service.Base1ExtB
%>

<%script type="ecore.EClass" name="ecoreTemplate" file="test.txt"%>

<%script type="ecore.EClass" name="isDocumentedTemplate" post="trim()"%>
<%isDocumented()%>

<%script type="ecore.EClass" name="isBTemplate" post="trim()"%>
<%isB()%>