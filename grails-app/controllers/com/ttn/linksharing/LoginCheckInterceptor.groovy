package com.ttn.linksharing


class LoginCheckInterceptor {

    LoginCheckInterceptor() {
        log.info "-----------------LoginCheckInterceptor----------------------------"
//        matchAll().excludes(controller: "login")
    }

    boolean before() {
        if (!session.user) {
            redirect(controller: 'login', action: 'index')
        }
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
