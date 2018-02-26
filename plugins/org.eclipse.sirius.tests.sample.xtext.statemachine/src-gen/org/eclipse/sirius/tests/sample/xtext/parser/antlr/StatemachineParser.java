/*******************************************************************************
* Copyright (c) 2018 Obeo.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Obeo - initial API and implementation
*******************************************************************************/
package org.eclipse.sirius.tests.sample.xtext.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.sirius.tests.sample.xtext.parser.antlr.internal.InternalStatemachineParser;
import org.eclipse.sirius.tests.sample.xtext.services.StatemachineGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class StatemachineParser extends AbstractAntlrParser {

	@Inject
	private StatemachineGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalStatemachineParser createParser(XtextTokenStream stream) {
		return new InternalStatemachineParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "Statemachine";
	}

	public StatemachineGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(StatemachineGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
