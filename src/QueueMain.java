import java.util.Random;

public class QueueMain {

	private static final int serviceCapacity=10;
	private static final int[]  states=new int[serviceCapacity+1];
	private static final double mu=60; // process rate per hour
	private static final double lambda=10;	//arrival rate per hour
	private static final int service=1;
	private static final double runHour=10000;
	private static final boolean showArrive=false;
	private static final boolean showCompletion=false;
	private static final boolean showRefused=true;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if(mu*service<lambda){
			System.out.println("System is unstable");
			System.exit(0);

		}

		double scaleLambda=lambda/3600;		//arrive rate per second
		double scaleMu=mu/3600;				//process rate per second for single server
		Random rand=new Random();

		int peopleIn=0;		//people in the system
		int time=0;			//clock
		int arrive=0;		//total customers arrived
		int processed=0;	//total processed jobs
		int peopleQueue=0;	//total number of people in the queue
		double totalWait=0;	//total wait time in the system
		double queueWait=0;	//total wait time in the queue
		double activeService=service;
		while(time<3600*runHour){
			peopleQueue=Math.max(0,peopleIn-service);
			totalWait+=peopleIn;
			queueWait+=peopleQueue;
			activeService=Math.min(service, peopleIn);	//number of working services
			if(scaleLambda>=rand.nextDouble() ){
				if(peopleIn<serviceCapacity){
					arrive++;
					peopleIn++;
					if(showArrive){
						System.out.println(arrive+"th arrival came to system at "+(time/3600)+":"+((time%3600)/60)+":"+(time%60)+" "
								+ ". There are "+ peopleIn+" people now.");
					}
				}else{
					if(showRefused){
						System.out.println("At "+(time/3600)+":"+((time%3600)/60)+":"+(time%60)+" a customer is refused"
								+ " due to capacity issues");
					}
				}
			}
			if(activeService*scaleMu>rand.nextDouble()){
				//peopleIn= peopleIn==0? 0: peopleIn-1;
				peopleIn--;
				processed++;
				if(showCompletion){
					System.out.println(processed+"th job is finished at "+(time/3600)+":"+((time%3600)/60)+":"+(time%60)+" "
							+ ". There are "+ peopleIn+" people now.");
				}
			}


			states[peopleIn]++;
			time++;
		}

		System.out.println("After "+runHour+" hours, there are "+peopleIn+" people in the system.");
		System.out.println(arrive+" people came and "+processed+" people have their job done.");
		printProbs();
		System.out.println("Lq="+queueWait/time);
		System.out.println("L="+totalWait/time);
		System.out.println("Wq="+queueWait/(time*lambda));
		System.out.println("W="+totalWait/(time*lambda));


	}

	private static double stateProb(int state){
		return states[state]/(3600*runHour);
	}
	private static void printProbs(){
		for(int i=0;i<states.length;i++){
			System.out.println("P"+i+"= "+stateProb(i));
		}
	}
}
