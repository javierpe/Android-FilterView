# Android-FilterView

Build a simple Filter view with customizable controls.

# Usage

* Create a section
```java
SingleSection singleSection = new SingleSection.Builder("Category", 1)
                .setSectionNameColor(R.color.colorAccent)
                .addOption(new SingleOption("CUSTOMER", R.color.colorAccent,
                        R.drawable.ic_account_black_24dp, R.color.colorBackground,
                        R.color.colorAccent, 2, R.color.colorAccent))
                .addOption(new SingleOption("ORGANIZATION", R.color.colorAccent,
                        R.drawable.ic_layers_black_24dp, R.color.colorBackground,
                        R.color.colorAccent, 2, R.color.colorAccent))
                .build().setOnSingleOptionListener(new OnSingleOptionListener() {
                    @Override
                    public void onClick(SingleOption option) {

                    }
                });
```

* Create FilterView instance and add section 
``` java
new FilterView.Builder(this)
                .withTitle("Aplicar")
                .setToolbarVisible(true)
                .withTitleColor(R.color.colorAccent)
                .withDivisorColor(R.color.colorAccent)
                .setCloseIconColor(R.color.colorAccent)
                .addSection(singleSection)
                .build()
                .setOnFilterViewResultListener(new OnFilterViewResultListener() {
                    @Override
                    public void onResult(JSONArray data) {
                        System.out.println(data.toString());
                    }
                }).show();
```
Call to ```java show(); ``` method to display FilterView and ```java setOnFilterViewResultListener(...) ``` to return JSONArray with data filtered.

# Sections

* SingleSection with SingleOption control.
* SliderSection with SliderOption control.
* TagSection with String tags.
* ExtraSection with:
  * ExtraBoolean
  * ExtraCurrencyEditText
  * ExtraDate
  * ExtraHSM
  * ExtraList
  * ExtraEditText
