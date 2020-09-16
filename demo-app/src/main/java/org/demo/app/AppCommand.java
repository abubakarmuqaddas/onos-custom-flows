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

import javafx.util.Pair;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.onlab.packet.VlanId;
import org.onlab.util.Frequency;
import org.onosproject.cli.AbstractShellCommand;
import org.onosproject.core.ApplicationId;
import org.onosproject.net.*;
import org.onosproject.net.behaviour.ExtensionSelectorResolver;
import org.onosproject.net.behaviour.ExtensionTreatmentResolver;
import org.onosproject.net.device.DeviceService;
import org.onosproject.net.driver.*;
import org.onosproject.net.flow.*;
import org.onosproject.net.flow.criteria.ExtensionSelector;
import org.onosproject.net.flow.criteria.ExtensionSelectorType;
import org.onosproject.net.flow.instructions.ExtensionPropertyException;
import org.onosproject.net.flow.instructions.ExtensionTreatmentType;
import org.onosproject.net.flow.instructions.Instructions;


import javax.validation.constraints.Null;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Sample Apache Karaf CLI command
 */
@Service
@Command(scope = "onos", name = "demoapp",
        description = "Sample Apache Karaf CLI command")
public class AppCommand extends AbstractShellCommand {

    @Override
    protected void doExecute() {

        DeviceService deviceService = get(DeviceService.class);
        DriverService driverService = get(DriverService.class);
        ApplicationId applicationID = appId();
        FlowRuleService flowRuleService = get(FlowRuleService.class);
        LinkedList<FlowRule> flowRules = new LinkedList<>();

        DeviceId deviceId = null;

        String deviceAId="netconf:192.168.46.4:2022";
        String clientPortA = "10103";
        String vlanAin = "100";
        String vlanAout = "200";
        String linePort = "10101";
        int priority = 100;

        for(Device device: deviceService.getDevices()){
            if(device.id().toString().equals(deviceAId)){
                deviceId = device.id();
            }
        }

        ConnectPoint clientConnectPoint = createConnectPoint(deviceId, clientPortA);
        ConnectPoint lineConnectPoint = createConnectPoint(deviceId, linePort);
        Driver driver = driverService.getDriver(deviceId);
        DriverHandler handler = new DefaultDriverHandler(new DefaultDriverData(driver, deviceId));
        ExtensionSelectorResolver selectorResolver = handler.behaviour(ExtensionSelectorResolver.class);



        // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*

        TrafficSelector.Builder selectorBuilder = DefaultTrafficSelector.builder();
        TrafficTreatment.Builder treatmentBuilder = DefaultTrafficTreatment.builder();
        ExtensionSelector extensionSelector = selectorResolver.getExtensionSelector(new ExtensionSelectorType(500));

        HashMap<PortNumber, Pair<VlanId, VlanId>> vlanAssignment = new HashMap<>();
        vlanAssignment.put(clientConnectPoint.port(), new Pair<>(VlanId.vlanId(vlanAin), VlanId.vlanId(vlanAout)));

        try {
            extensionSelector.setPropertyValue("vlanPortAssignment", vlanAssignment);
        }
        catch (ExtensionPropertyException e) {
            log.error("Error setting extension property", e);
        }

        selectorBuilder.matchInPort(clientConnectPoint.port());
        //selectorBuilder.extension(extensionSelector, deviceId);

        treatmentBuilder.setOutput(lineConnectPoint.port());

        FlowRule flowRule1 = DefaultFlowRule.builder()
                .forDevice(deviceId)
                .withSelector(selectorBuilder.build())
                .withTreatment(treatmentBuilder.build())
                .withPriority(priority)
                .fromApp(applicationID)
                .makePermanent()
                .build();

        flowRules.add(flowRule1);


        */

        ////////////////////////////////////////

        /*

        TrafficSelector.Builder selectorBuilder2 = DefaultTrafficSelector.builder();
        TrafficTreatment.Builder treatmentBuilder2 = DefaultTrafficTreatment.builder();

        ExtensionSelector extensionSelector2 = selectorResolver.getExtensionSelector(new ExtensionSelectorType(500));

        HashMap<PortNumber, Pair<VlanId, VlanId>> testMap2 = new HashMap<>();
        testMap2.put(connectPointB.port(), new Pair<>(VlanId.vlanId("500"), VlanId.vlanId("600")));

        try {
            extensionSelector2.setPropertyValue("localVar", 20);
            extensionSelector2.setPropertyValue("localMap", testMap2);
        }
        catch (ExtensionPropertyException e) {
            log.error("Error setting extension property", e);
        }

        selectorBuilder2.matchInPort(connectPointA.port());
        selectorBuilder2.extension(extensionSelector2, deviceId);

        treatmentBuilder2.setOutput(connectPointB.port());

        FlowRule flowRule2 = DefaultFlowRule.builder()
                .forDevice(deviceId)
                .withSelector(selectorBuilder2.build())
                .withTreatment(treatmentBuilder2.build())
                .withPriority(priority)
                .fromApp(applicationID)
                .makePermanent()
                .build();
        //flowRuleService.applyFlowRules(flowRule2);

        */

        ////////////////////////////////////////

        TrafficSelector.Builder selectorBuilder3 = DefaultTrafficSelector.builder();
        TrafficTreatment.Builder treatmentBuilder3 = DefaultTrafficTreatment.builder();

        ExtensionSelector extensionSelector3 = selectorResolver.getExtensionSelector(new ExtensionSelectorType(500));

        HashMap<PortNumber, Pair<VlanId, VlanId>> vlanAssignment3 = new HashMap<>();
        vlanAssignment3.put(clientConnectPoint.port(), new Pair<>(VlanId.vlanId("500"), VlanId.vlanId("600")));

        try {
            extensionSelector3.setPropertyValue("vlanPortAssignment", vlanAssignment3);
        }
        catch (ExtensionPropertyException e) {
            log.error("Error setting extension property", e);
        }

        selectorBuilder3.matchInPort(lineConnectPoint.port());
        selectorBuilder3.extension(extensionSelector3, deviceId);

        OchSignal lambda = new OchSignal(
                GridType.DWDM,
                ChannelSpacing.CHL_50GHZ,
                0,
                4
        );

        treatmentBuilder3.add(Instructions.modL0Lambda(lambda));
        treatmentBuilder3.setOutput(lineConnectPoint.port());

        FlowRule flowRule3 = DefaultFlowRule.builder()
                .forDevice(deviceId)
                .withSelector(selectorBuilder3.build())
                .withTreatment(treatmentBuilder3.build())
                .withPriority(priority)
                .fromApp(applicationID)
                .makePermanent()
                .build();

        flowRules.add(flowRule3);


        for (FlowRule flowRule : flowRules) {
            flowRuleService.applyFlowRules(flowRule);
        }


    }

    private ConnectPoint createConnectPoint(DeviceId deviceId, String portnumber) {

        DeviceService deviceService = get(DeviceService.class);

        List<Port> ports = deviceService.getPorts(deviceId);

        for (Port port : ports) {
            if (portnumber.equals(port.number().name())) {
                return new ConnectPoint(deviceId, port.number());
            }
        }

        return null;
    }


}
