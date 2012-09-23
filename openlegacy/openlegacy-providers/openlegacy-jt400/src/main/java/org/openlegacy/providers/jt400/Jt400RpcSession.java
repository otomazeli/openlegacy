package org.openlegacy.providers.jt400;

import com.ibm.as400.access.AS400;

import org.openlegacy.exceptions.EntityNotFoundException;
import org.openlegacy.rpc.RpcEntity;
import org.openlegacy.rpc.RpcSession;
import org.openlegacy.rpc.definitions.RpcEntityDefinition;
import org.openlegacy.rpc.services.RpcEntitiesRegistry;
import org.openlegacy.support.AbstractSession;

import java.util.Set;

import javax.inject.Inject;

public class Jt400RpcSession extends AbstractSession implements RpcSession {

	private AS400 as400Session;
	private String hostName;

	@Inject
	private RpcEntitiesRegistry rpcEntitiesRegistry;

	public Object getDelegate() {
		return as400Session;
	}

	public Jt400RpcSession(String hostName) {
		this.hostName = hostName;
	}

	public <T> T getEntity(Class<T> entityClass, Object... keys) throws EntityNotFoundException {
		return null;
	}

	public Object getEntity(String entityName, Object... keys) throws EntityNotFoundException {
		return null;
	}

	public AS400 getAs400Session() {
		if (as400Session == null) {
			as400Session = new AS400(hostName);
		}
		return as400Session;
	}

	public void disconnect() {
		if (as400Session != null) {
			as400Session.disconnectAllServices();
		}
	}

	public boolean isConnected() {
		return as400Session != null;
	}

	public <R extends RpcEntity> R getEntity(R rpcEntity) {
		RpcEntityDefinition rpcEntityDefinition = rpcEntitiesRegistry.get(rpcEntity.getClass());

		Set<String> fieldNames = rpcEntityDefinition.getFieldsDefinitions().keySet();
		for (String fieldName : fieldNames) {

		}
		return null;
	}
}
