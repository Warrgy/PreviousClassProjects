
private <- read.csv("privateTransportation.csv", FALSE, ",")
public <- read.csv("publicTransportation.csv", FALSE, ",")
privateYear <- private$V1
privateValue <- private$V6
publicYear <- public$V1
publicValue <- public$V6

#Private transportation data
plot(x = privateYear, y = privateValue, type = "n", main = "Transportation in Canada", xlab = "Year", ylab = "CPI Value")
lines(x = privateYear, y = privateValue, col = "red", lwd = 2)
lines(x = publicYear, y = publicValue, col = "blue", lwd = 2)

