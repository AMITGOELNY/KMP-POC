import SwiftUI
import Shared

extension FeedState: Identifiable { }
extension FeedItem: Identifiable { }

struct ContentView: View {
    @StateObject var viewModelStoreOwner = SharedViewModelStoreOwner<FeedViewModel>()
    
    var body: some View {
        VStack {
            NavigationView {
                VStack(spacing: 0) {
                    Observing(viewModelStoreOwner.instance.state) { state in
                        switch onEnum(of: state.feed) {
                        case .loading:
                            Text("loading")
                        case .empty:
                            Text("empty")
                        case .error:
                            Text("error")
                        case .loaded(let success):
                            List(success.data?.items ?? []) { item in
                                Text(item.title)
                            }
                            .listStyle(.plain)
                            .navigationBarTitle(Text("Trending News"))
                            .onAppear {
                                UITableView.appearance().separatorStyle = .none
                            }
                        }
                    }
                }
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
                .padding()
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
