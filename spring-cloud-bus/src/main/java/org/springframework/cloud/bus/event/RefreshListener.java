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

package org.springframework.cloud.bus.event;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ApplicationListener;

/**
 * @author Spencer Gibb
 */
public class RefreshListener
		implements ApplicationListener<RefreshRemoteApplicationEvent> {

	private static Log log = LogFactory.getLog(RefreshListener.class);

	private ContextRefresher contextRefresher;

	public RefreshListener(ContextRefresher contextRefresher) {
		this.contextRefresher = contextRefresher;
	}

	@Override
	public void onApplicationEvent(RefreshRemoteApplicationEvent event) {
		Set<String> keys = this.contextRefresher.refresh();
		log.info("Received remote refresh request. Keys refreshed " + keys);
	}

}
