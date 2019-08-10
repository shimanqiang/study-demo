package me.shimanqiang.agent;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.sun.tools.attach.spi.AttachProvider;

import java.util.List;

/**
 * @author
 * @since 2019/7/15 11:09
 */
public class TestAttachApi {

    public static void main(String[] args) throws Exception {
        //attach api test
        new TestAttachApi().attachApiInvoke();
    }

    public void attachApiInvoke() {
        List<VirtualMachineDescriptor> vmds = VirtualMachine.list();
        if (vmds != null) {
            for (VirtualMachineDescriptor vmd : vmds) {
                System.out.println(vmd.displayName());
                AttachProvider provider = vmd.provider();
                System.out.println(provider.name());
                if ("me.shimanqiang.agent.TestAgentMain".equals(vmd.displayName())) {
                    try {
                        VirtualMachine vm = VirtualMachine.attach(vmd.id());
                        vm.loadAgent("target/study-demo-1.0-SNAPSHOT.jar");
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
