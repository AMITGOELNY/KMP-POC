import SwiftUI
import Shared

extension FeedState: Identifiable { }
extension FeedItem: Identifiable { }

struct ContentView: View {
    @StateObject var viewModelStoreOwner = SharedViewModelStoreOwner<FeedViewModel>()
    
    var body: some View {
            NavigationView {
                Group {
                    Observing(viewModelStoreOwner.instance.state) { state in
                        switch onEnum(of: state.feed) {
                        case .loading:
                            ProgressView()
                                .padding()
                        case .empty:
                            Text("No News")
                                .font(.headline)
                                .padding()
                        case .error:
                            Text("Error")
                                .font(.headline)
                                .padding()
                        case .loaded(let success):
                            newsFeedList(feedItems: success.data?.items ?? [])
                        }
                    }
                }
                .navigationTitle("Trending News")
                .frame(maxWidth: .infinity)
            }
    }
}

@ViewBuilder
func newsFeedList(feedItems: [FeedItem]) -> some View {
    
    ScrollView {
        VStack(spacing: 12) {
            ForEach(feedItems) { feedItem in
                HStack(spacing: 10) {
                    
                    AsyncImage(url: URL(string: feedItem.image)) { image in
                        image
                            .resizable()
                    } placeholder: {
                        ProgressView()
                    }
                    .frame(width: 100, height: 100)
                    .clipShape(
                        .rect(
                            topLeadingRadius: 12,
                            bottomLeadingRadius: 12,
                            bottomTrailingRadius: 0,
                            topTrailingRadius: 0
                        )
                    )
                    
                    VStack(alignment: .leading, spacing: 5) {
                        Text(feedItem.title)
                            .font(.headline)
                            .lineLimit(2)
                            .padding(.top, 6)
                        
                        Spacer()
                        
                        Text(feedItem.site)
                            .font(.caption)
                            .foregroundStyle(Color.orange)
                            .padding(.bottom, 6)
                    }
                    
                    Spacer()
                    
                }
                .frame(maxWidth: .infinity)
                .frame(height: 100)
                .clipShape(RoundedRectangle(cornerRadius: 12, style: .continuous))
                .overlay(
                    RoundedRectangle(cornerRadius: 12)
                        .stroke(Color.gray, lineWidth: 0.8)
                )
                .padding(.vertical, 2)
            }
        }
        .padding()
    }

}
