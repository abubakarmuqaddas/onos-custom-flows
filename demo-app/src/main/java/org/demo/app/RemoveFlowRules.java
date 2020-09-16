/*
 * Copyright 2019-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.demo.app;

import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.onosproject.cli.AbstractShellCommand;
import org.onosproject.core.ApplicationId;
import org.onosproject.net.*;
import org.onosproject.net.device.DeviceService;
import org.onosproject.net.flow.*;


/**
 * Sample Apache Karaf CLI command
 */
@Service
@Command(scope = "onos", name = "demoapp-remove",
        description = "Sample Apache Karaf CLI command")
public class RemoveFlowRules extends AbstractShellCommand {

    @Override
    protected void doExecute() {

        DeviceService deviceService = get(DeviceService.class);
        FlowRuleService flowRuleService = get(FlowRuleService.class);
        ApplicationId applicationID = appId();

        DeviceId deviceId = null;

        String deviceAId="netconf:192.168.46.4:2022";

        for(Device device: deviceService.getDevices()){
            if(device.id().toString().equals(deviceAId)){
                deviceId = device.id();
            }
        }

        flowRuleService.getFlowEntries(deviceId).forEach(
                flowEntry -> flowRuleService.removeFlowRules((FlowRule)flowEntry)
        );

    }
}
