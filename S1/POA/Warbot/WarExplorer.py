
def actionWarExplorer():

    if (isBagFull()) :
        setDebugString("Bag full return base")

        percepts = getPerceptsAlliesByType(WarAgentType.WarBase);

        if((percepts is None) or (len(percepts) == 0)):
            #broadcastMessageToAll("whereAreYou", "");

            messages = getMessages();

            for message in messages :
                if(message.getSenderType() == WarAgentType.WarBase):
                    setHeading(message.getAngle());

            broadcastMessageToAgentType(WarAgentType.WarBase, "whereAreYou", "");

        else :
            base = percepts[0];

            if(base.getDistance() > maxDistanceGive()):
                setHeading(base.getAngle());
                return move();
            else:
                setIdNextAgentToGive(base.getID());
                return give();
    else :
        setDebugString("Chercher food");
        percepts_ressource = getPerceptsResources();

        if((percepts is None) or (len(percepts_ressource) == 0)):

            setRandomHeading(20);
            broadcastMessageToAgentType(WarAgentType.self, "No food here", "");

        else :
            for ressource in percepts_ressource :
                if(ressource.getType().equals(WarAgentType.WarFood)):
                    setHeading(ressource.getAngle());
                    if(ressource.getDistance() < getMaxDistanceTakeFood()):
                        broadcastMessageToAgentType(WarAgentType.self, "food around here", "");
                        return take();


    if (isBlocked()) :
        setRandomHeading()

    return move()
