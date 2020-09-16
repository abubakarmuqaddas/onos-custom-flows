package org.onosproject.drivers.odtn.openconfig;

import javafx.util.Pair;
import org.onlab.packet.VlanId;
import org.onosproject.net.PortNumber;
import org.onosproject.net.flow.AbstractExtension;
import org.onosproject.net.flow.criteria.ExtensionSelector;
import org.onosproject.net.flow.criteria.ExtensionSelectorType;

import java.util.HashMap;

public class NewClientFlow extends AbstractExtension implements ExtensionSelector {

    private HashMap<PortNumber, Pair<VlanId, VlanId>> vlanPortAssignment;

    NewClientFlow(){
        vlanPortAssignment = null;
    }

    @Override
    public ExtensionSelectorType type() {
        return null;
    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }

    @Override
    public void deserialize(byte[] data) {

    }
}
