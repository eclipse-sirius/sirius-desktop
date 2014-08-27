<%
metamodel http://www.eclipse.org/emf/2002/Ecore

import org.eclipse.sirius.service.Base2
%>

<%script type="ecore.EModelElement" name="ecoreTemplate" file="test.txt"%>

<%script type="ecore.EModelElement" name="isDocumentedTemplate" post="trim()"%>
<%isDocumented()%>