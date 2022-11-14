/*
 * Copyright (c) 2022, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.event.outbound.adapter.websubhub.internal.ds;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.wso2.carbon.event.outbound.adapter.websubhub.WebSubHubAdapterService;
import org.wso2.carbon.event.outbound.adapter.websubhub.internal.ClientManager;
import org.wso2.carbon.event.outbound.adapter.websubhub.internal.services.WebSubHubAdapterServiceImpl;

/**
 * WebSubHub Outbound Event Adapter service component.
 */
@Component(
        name = "outbound.websubhub.event.adapter.service.component",
        immediate = true)
public class WebSubHubEventAdapterServiceComponent {

    private static final Log log = LogFactory.getLog(WebSubHubEventAdapterServiceComponent.class);

    @Activate
    protected void activate(ComponentContext context) {

        try {
            WebSubHubAdapterServiceImpl webSubHubEventAdapter = new WebSubHubAdapterServiceImpl();
            context.getBundleContext().registerService(WebSubHubAdapterService.class.getName(),
                    webSubHubEventAdapter, null);
            WebSubHubEventAdapterDataHolder.getInstance().setClientManager(new ClientManager());
            if (log.isDebugEnabled()) {
                log.debug("Successfully deployed the websubhub event adapter service");
            }
        } catch (Throwable e) {
            log.error("Can not create the websubhub event adapter service: " + e.getMessage(), e);
        }
    }
}

