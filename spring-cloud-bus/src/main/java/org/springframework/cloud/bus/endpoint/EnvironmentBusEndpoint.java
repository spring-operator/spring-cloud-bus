/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.bus.endpoint;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.cloud.bus.event.EnvironmentChangeRemoteApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author Spencer Gibb
 */
@Endpoint(id = "bus-env") // TODO: document
public class EnvironmentBusEndpoint extends AbstractBusEndpoint {

	public EnvironmentBusEndpoint(ApplicationEventPublisher context, String id) {
		super(context, id);
	}

	@WriteOperation
	public void busEnvWithDestination(String name, String value,
			@Selector String destination) { // TODO: document params
		Map<String, String> params = Collections.singletonMap(name, value);
		publish(new EnvironmentChangeRemoteApplicationEvent(this, getInstanceId(),
				destination, params));
	}

	@WriteOperation
	public void busEnv(String name, String value) { // TODO: document params
		Map<String, String> params = Collections.singletonMap(name, value);
		publish(new EnvironmentChangeRemoteApplicationEvent(this, getInstanceId(), null,
				params));
	}

}
