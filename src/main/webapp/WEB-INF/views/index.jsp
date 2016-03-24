<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@
taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" />
</head>
<body>
	<div class="wrap shadow-bottom" style="text-align: center;">
		<h1>Rock, Paper, Scissors Game</h1>
		<div class="row" style="text-align: middle;">
			<c:if test="${result.status}">
				<div id="results" class="alert alert-success alert-result">
					<strong>Game Over! - <span id="numberOfRounds"></span>
						rounds.
					</strong><br /> Player 1 won ${result.playerOneWins} times. <br />
					Player 2 won ${result.playerTwoWins} times. <br /> ${result.draws} draws. <br />
					<p>
						<strong><a href="${pageContext.request.contextPath}/game">Go
								again</a></strong>
					</p>
				</div>
				<div class="alert alert-info alert-bot">100 iterations, First
					player plays random and the second chooses rock always.</div>
			</c:if>
		</div>
	</div>
</body>
</html>