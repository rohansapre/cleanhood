/**
 * Created by Amritansh on 4/8/2017.
 */
module.exports =function(app, Model){

    function createUser(req, res) {
        var user=req.body;
        Model
            .createUser(user)
            .then(
                function (user) {
                    res.json(user);
                },
                function (error) {
                    res.status(404).send(error);
                }
            )
    };

    function deleteUser(req, res) {
        var UserId= req.params.userId;
        model
            .deleteUser()
    }
};