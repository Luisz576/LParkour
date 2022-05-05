import express, { json } from "express"
import mongoose from "mongoose"
const routes = require("./routes")

const { port, mongoose_link } = require("../config.json")

const app: express.Application = express()

app.use(express.json())
app.use(routes)

// mongoose.connect(mongoose_link)

app.listen(port, () => {
    console.log("Server is running on port " + port)
})