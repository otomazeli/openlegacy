package org.openlegacy.rpc.definitions;

import org.openlegacy.definitions.support.AbstractEntityDefinition;

public class SimpleRpcEntityDefinition extends AbstractEntityDefinition<RpcFieldDefinition> implements RpcEntityDefinition {

	private String programPath;

	public SimpleRpcEntityDefinition(String entityName, Class<?> screenEntityClass) {
		super(entityName, screenEntityClass);
	}

	public String getProgramPath() {
		return programPath;
	}

	public void setProgramPath(String programPath) {
		this.programPath = programPath;
	}
}
