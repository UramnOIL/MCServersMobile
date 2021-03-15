import SwiftUI
import shared

func greet() -> String {
    return Greeting().greeting()
}
import SwiftUI

struct ContentView: View {
    @ObservedObject var model: ServersViewModel = ServersViewModel()
    var serversCount: Int {
        model.servers.count
    }

    var body: some View {
        NavigationView {
            VStack {
                Text("サーバーの数: \(serversCount)").padding()
                List(model.servers) { server in
         C           HStack {
                        Text(server.name)
                        Spacer()
                        if let currentPlayer = server.latestPing.currentPlayer, let maxPlayer = server.latestPing.maxPlayer {
                            Text("\(currentPlayer) / \(maxPlayer)")
                        }
                    }
                }
            }
            .navigationBarTitle("MCServers.jp", displayMode: .inline)
            .navigationBarItems(
                trailing: HStack {
                    Button(
                        action: {
                            model.loadServers()
                        },
                        label: {
                            Text("更新")
                        }
                    )
                }
            )
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
