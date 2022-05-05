import express from "express"
const playerScoreController = require("./controllers/PlayerScoreController")

const router:express.Router = express.Router()

router.get("/score", playerScoreController.show)
router.get("/setscore", playerScoreController.store)

module.exports = router