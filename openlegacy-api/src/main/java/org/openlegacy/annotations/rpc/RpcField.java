package org.openlegacy.annotations.rpc;

import org.openlegacy.FieldType;
import org.openlegacy.rpc.RpcFieldTypes;

public @interface RpcField {

	Direction direction();

	int size();

	Class<? extends FieldType> fieldType() default RpcFieldTypes.General.class;
}
