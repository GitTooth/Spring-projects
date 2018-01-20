<!DOCTYPE html>
<html>
<head>
    <title>React + Spring</title>
</head>
<body>
<div id='root'></div>

<script src="https://fb.me/react-15.0.1.js"></script>
<script src="https://fb.me/react-dom-15.0.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script type="text/babel">
    var Employee = React.createClass({
        render: function() {
            return (<div>employee</div>);
        }
    });
    var EmployeeTable = React.createClass({
        render: function() {
            return (<div>employee table</div>);
        }
    });

    var App = React.createClass({

        loadUsersFromServer: function () {
            var self = this;
            $.ajax({
                url: "http://localhost:8080/api/users"
            }).then(function (data) {
                self.setState({users: data._embedded.users});
            });
        },

        getInitialState: function () {
            return {users: []};
        },

        componentDidMount: function () {
            this.loadUsersFromServer();
        },

        render() {
            return ( <UserTable users={this.state.users}/> );
        }
    });

    ReactDOM.render(
        ReactDOM.render(<App />, document.getElementById('root') )
    );
</script>
</body>
</html>