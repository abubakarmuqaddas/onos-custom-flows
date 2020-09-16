package org.onosproject.drivers.odtn.openconfig;

import org.onosproject.net.behaviour.ExtensionSelectorResolver;
import org.onosproject.net.driver.AbstractHandlerBehaviour;
import org.onosproject.net.flow.criteria.ExtensionSelector;
import org.onosproject.net.flow.criteria.ExtensionSelectorType;


public class TerminalDeviceExtensionSelectorInterpreter extends AbstractHandlerBehaviour
        implements ExtensionSelectorResolver {

    @Override
    public ExtensionSelector getExtensionSelector(ExtensionSelectorType type) {

        if (type.toInt()==500) {
            return new NewClientFlow();
        }

        throw new UnsupportedOperationException(
                "Driver does not support extension type " + type.toString()
        );

    }
}
