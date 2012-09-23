package org.openlegacy.rpc.definitions;

import org.openlegacy.FieldType;
import org.openlegacy.definitions.support.AbstractFieldDefinition;

public class SimpleRpcFieldDefinition extends AbstractFieldDefinition<RpcFieldDefinition> implements RpcFieldDefinition {

	public SimpleRpcFieldDefinition(String name, Class<? extends FieldType> type) {
		super(name, type);
	}

}
