// WARNING
//
// This file has been generated automatically by Xamarin Studio to store outlets and
// actions made in the UI designer. If it is removed, they will be lost.
// Manual changes to this file may not be handled correctly.
//
using Foundation;
using System.CodeDom.Compiler;

namespace xamarin.iOS
{
	[Register ("GraphicsViewController")]
	partial class GraphicsViewController
	{
		[Outlet]
		UIKit.UILabel benchmarkResult { get; set; }

		[Outlet]
		UIKit.UIImageView imageView { get; set; }

		[Outlet]
		UIKit.UIButton runBtn { get; set; }

		[Action ("run:")]
		partial void run (Foundation.NSObject sender);
		
		void ReleaseDesignerOutlets ()
		{
			if (runBtn != null) {
				runBtn.Dispose ();
				runBtn = null;
			}

			if (imageView != null) {
				imageView.Dispose ();
				imageView = null;
			}

			if (benchmarkResult != null) {
				benchmarkResult.Dispose ();
				benchmarkResult = null;
			}
		}
	}
}
