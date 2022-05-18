db.createUser(
    {
        user: "arrow",
        pwd: "mysecretpassword",
        roles: [
            {
                role: "readWrite",
                db: "arrow"
            }
        ]
    }
);
db.createCollection("arrow");