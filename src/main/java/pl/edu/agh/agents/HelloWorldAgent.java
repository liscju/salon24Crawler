package pl.edu.agh.agents;

import org.janusproject.kernel.status.Status;

public class HelloWorldAgent extends org.janusproject.kernel.agent.Agent{
    @Override
    public Status live() {
        Status status = super.live() ;
        if (status.isSuccess()) {
            // Do something here
        }
        return status ;
    }
}
