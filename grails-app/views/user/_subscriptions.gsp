<div class="panel panel-primary ">
    <div class="panel-heading" style="background: #ADFF2F">
        <h4>Subscriptions</h4>
    </div>

    <g:each in="${subscriptionList}" var="subscribedTopics">
        <div class="panel-body">
            <div class="col-md-12">
                <div class="col-md-3">
                    <ls:userImage id="${session.user.id}" height="110" width="110"/>
                </div>


                <div class="col-lg-9">
                    <div class="col-lg-12">

                        <div class="input-group">
                            <p class="text-primary">@${subscribedTopics.topicName}</p>
                        </div>
                    </div>


                    <div class="col-lg-12">
                        <div class="col-sm-6">
                            <h3 class="text-muted">@${session.user.firstName}</h3>
                            <h3 class="text-muted">Unsubscribe</h3>

                        </div>

                        <div class="col-sm-3">
                            <h4 class="text-muted pull-left">Subscriptions
                                <p class="text-primary">50</p>
                            </h4>
                        </div>

                        <div class="col-sm-3">
                            <h4 class="text-muted  pull-right">Post
                                <p class="text-primary"></p>
                            </h4>
                        </div>

                    </div>
                </div>

            </div>
            <br>
            <br>

            <div class="col-lg-12">
                %{--<select class="col-lg-4" name="subscriptionSeriousness">--}%
                %{--<option class="placeholder" select disabled value="">SERIOUSNESS</option>--}%
                %{--<option value="VERYSERIOUS">Very Serious</option>--}%
                %{--<option value="SERIOUS">Serious</option>--}%
                %{--<option value="CASUAL">Casual</option>--}%
                %{--</select>--}%
                <div class="col-lg-1">
                </div>

                <div class="col-lg-4">
                    <select class="form-control " name="topicSeriousness">
                        <span class="caret"></span>
                        <option value="VERYSERIOUS">Very Serious</option>
                        <option value="SERIOUS">Serious</option>
                        <option value="CASUAL">Casual</option>
                    </select>
                </div>

                <div class="col-lg-4">
                    <select class="form-control " name="topicVisibility">
                        <span class="caret"></span>
                        <option value="">Public</option>
                        <option value="">Private</option>
                    </select>
                </div>
                %{--<div>--}%

                %{--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" style="display: block">Seriousness--}%
                %{--<span class="caret"></span></button>--}%
                %{--<ul class="dropdown-menu">--}%
                %{--<li><a href="#">Very Serious</a></li>--}%
                %{--<li><a href="#">Serious</a></li>--}%
                %{--<li><a href="#">Casual</a></li>--}%
                %{--</ul>--}%
                %{--</div>--}%

                <div class="col-lg-1" style="padding: 0px">
                    <a href="#myModal2" data-toggle="modal" data-target="#myModal2">
                        <i class="fa fa-envelope" style="font-size:30px;color:yellow"></i>
                    </a>
                </div>

                <div class="col-lg-1" style="padding: 0px">
                    <a href="#myModal4" data-toggle="modal" data-target="#myModal4">
                        <i class="fa fa-file" style="font-size:30px;color:black"></i>
                    </a>
                </div>

                <div class="col-lg-1" style="padding: 0px">
                    <a href="#myModal4" data-toggle="modal" data-target="#myModal4">
                        <i class="glyphicon glyphicon-trash" style="font-size:30px;color:green"></i>
                    </a>
                </div>
            </div>
        </div>
    </g:each>
</div>

