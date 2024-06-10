import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        DIKt.iniIosKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
