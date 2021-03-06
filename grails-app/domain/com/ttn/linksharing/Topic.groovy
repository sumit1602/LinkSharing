package com.ttn.linksharing

import com.ttn.linksharing.enums.Seriousness
import com.ttn.linksharing.enums.Visibility

class Topic {
    Visibility visibility
    String name
    User createdBy
    Date dateCreated
    Date lastUpdated

    static hasMany = [subscriptions: Subscription, resources: Resource]
    static mapping = {
        sort name: 'asc'
        subscriptions cascade: 'all-delete-orphan'
        resources cascade: 'all-delete-orphan'
        readingItems cascade: 'all-delete-orphan'
    }


    def afterInsert() {
        withNewSession { session ->
            Subscription subscription = new Subscription(seriousness: Seriousness.VERY_SERIOUS, user: createdBy, topic: this)
            if (subscription.save(flush: true)) {
                log.info "Topic ${subscription} is subscribed to the topic creater"
            } else {
                log.info "${subscription.errors.allErrors}"
            }
        }
    }

    static getTrendingTopics() {
        List<Topic> trendingTopics = Resource.createCriteria().list() {
            projections {
                createAlias('topic', 'tp')
                groupProperty('tp.id')
                property('tp.name')
                property('tp.visibility')
                count('tp.id', 'count')
                property('tp.createdBy')
            }
            eq('tp.visibility', Visibility.PUBLIC)
            groupProperty('topic')
            order('count', 'desc')
            maxResults(5)
        }
       List<Topic> result=[]
        trendingTopics.each {result.add(new Topic(name:  it[1], visibility: it[2], createdBy: it[4]))}
        result
    }


    List<User> getSubscribedUsers(){
        List<User> subscribedUsers = this.subscriptions.user.toList()
        subscribedUsers
    }

    def isPublic(Topic topic){
        if(topic.visibility.PUBLIC){
            log.info("Topic is public")
            return true
        }else{
            log.info("Topic is private")
            return false
        }
    }

    def canViewedBy(User user){
        Subscription subscription = Subscription.findByUserAndTopic(user,this)
        if(isPublic(this) || subscription || user.isAdmin()){
            log.info("Can be viewed by ${user}")
            return true
        }else{
            log.info("Cannot be viewed")
            return false
        }
    }

    def getScore(){

    }


    static constraints = {
        name(nullable: false, blank: false, unique: 'createdBy')
        createdBy nullable: false
        visibility nullable: false
    }


    @Override
    public String toString() {
        return "${name} "
    }

}
