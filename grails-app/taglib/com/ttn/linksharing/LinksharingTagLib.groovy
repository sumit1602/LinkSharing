package com.ttn.linksharing

class LinksharingTagLib {
    static defaultEncodeAs = [taglib:'text']
    static namespace = "ls"
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    def userImage = { attr, body ->
    out << "<img src='${createLink(controller: 'user', action: 'image', params: [id: attr.id])}' " +
            " height='${attr.height}' width='${attr.width}'>"
    }
    def markasRead = { attrs, body ->
        if (session.user && attrs.resource) {
            ReadingItem readingItem = ReadingItem.findByUserAndResource(session.user, attrs.resource)
            if (readingItem) {
                if (readingItem.isRead) {
                    body = "<u>Mark As Unread</u>"

                } else {
                    body = "<u>Mark As Read</u>"
                }
            }
        }
    }
    def trendingTopics = {

        out << render(template: '/topic/trendingTopics')
    }

    def topPosts = {
        out << render(template: '/resource/topPosts')
    }
    def canDeleteResource = { attrs, body ->
        if (session.user) {
            User user = session.user
            Long id = attrs.resourceId
            if (user.canDeleteResource(id)) {
                body = "Delete"
                out << g.link(body, controller: "resource", action: "delete")
            }
        }
    }
    def showSubscribe = { attrs, body ->
        User user = session.user
        if (user && attrs.id) {
            if (user.isSubscribed(attrs.id)) {
                Subscription subscription = Subscription.findByUserAndTopic(user, Topic.get(attrs.id))
                body = "<u>Unsubscribe</u>"
                out << g.link([controller: "subscription", action: "delete"], body)
            } else {
                body = "<u>Subscribe</u>"
                out << g.link([controller: "subscription", action: "save"], body)
            }

        }

    }
    def subscriptionCount = { attrs, body ->
        Long userId = attrs.userId
        Long topicId = attrs.topicId
        if (userId) {
            out << Subscription.countByUser(User.load(userId))

        } else if (topicId) {

            out << Subscription.countByTopic(Topic.load(topicId))
        } else {
            out << "error"
        }


    }

    def resourceCount = { attrs, body ->
        Topic topic = Topic.load(attrs.userId)
        if (topic) {
            out << Resource.countByTopic(topic)
        } else {
            out << "error"
        }
    }

    def topicCount = { attrs, body ->
        User user = User.load(attrs.userId)
        if (user) {
            out << Topic.countByCreatedBy(user)
        }

    }


}
