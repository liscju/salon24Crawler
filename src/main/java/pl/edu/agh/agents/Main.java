package pl.edu.agh.agents;

import org.janusproject.kernel.Kernel;
import org.janusproject.kernel.agent.Kernels;

public class Main {

    public static void main(String[] args) {
        HelloWorldAgent a = new HelloWorldAgent();
        Kernel k = Kernels.get();
        k.launchLightAgent(a);
    }
}
