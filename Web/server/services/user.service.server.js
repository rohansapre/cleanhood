/**
 * Created by Amritansh on 4/8/2017.
 */
module.exports =function(app, Model){
    var userModel=Model.UserModel;
    app.get("/api/user",findUserByCredentials);
    app.get("/api/user/:userId", findUserById);
    app.put("/api/user/:userId", updateUser);
    app.delete("/api/user/:userId", deleteUser);
    app.post("/api/user", createUser);
    
    function createUser(req, res) {
        var user=req.body;
        userModel
            .createUser(user)
            .then(
                function (user) {
                    res.json(user);
                },
                function (error) {
                    res.status(404).send(error);
                }
            )
    }

    function deleteUser(req, res) {
        var userId= req.params.userId;
        userModel
            .deleteUser(userId)
            .then(
                function (user) {
                    res.sendStatus(200);
                },
                function (error) {
                    res.status(404).send(error);
                }
            )
    }
    
    function updateUser(req, res) {
        var userId=req.params.userId;
        var user=req.body;
        userModel
            .updateUser(userId,user)
            .then(
                function (user) {
                    res.status(200).send(user);
                },
                function (error) {
                    res.status(404).send(error);
                }
            )
    }
    
    function findUserById(req,res) {
        var userId=req.params.userId;
        userModel
            .findUserById(userId)
            .then(
                function (user) {
                    res.status(200).send(user);
                },
                function (error) {
                    res.status(404).send(error);
                }
            )
    }

    function findUserByCredentials(req,res) {
        var user=req.body;
        var username=user.username;
        var password=user.password;
        userModel
            .findUserByCredentials(username,password)
            .then(
                function (user) {
                    res.status(200).json(user);
                },
                function (error) {
                    res.status(404).send(error);
                }
            )
    }
};